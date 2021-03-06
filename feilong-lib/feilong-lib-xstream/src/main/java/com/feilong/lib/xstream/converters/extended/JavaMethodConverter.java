/*
 * Copyright (C) 2004, 2005 Joe Walnes.
 * Copyright (C) 2006, 2007, 2009, 2013, 2018 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 04. April 2004 by Joe Walnes
 */
package com.feilong.lib.xstream.converters.extended;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.feilong.lib.xstream.converters.ConversionException;
import com.feilong.lib.xstream.converters.Converter;
import com.feilong.lib.xstream.converters.MarshallingContext;
import com.feilong.lib.xstream.converters.SingleValueConverter;
import com.feilong.lib.xstream.converters.UnmarshallingContext;
import com.feilong.lib.xstream.core.ClassLoaderReference;
import com.feilong.lib.xstream.io.HierarchicalStreamReader;
import com.feilong.lib.xstream.io.HierarchicalStreamWriter;

/**
 * Converts a java.lang.reflect.Method to XML.
 * 
 * @author Aslak Helles&oslash;y
 * @author J&ouml;rg Schaible
 */
public class JavaMethodConverter implements Converter{

    private final SingleValueConverter javaClassConverter;

    /**
     * Construct a JavaMethodConverter.
     * 
     * @param classLoaderReference
     *            the reference to the {@link ClassLoader} of the XStream instance
     * @since 1.4.5
     */
    public JavaMethodConverter(ClassLoaderReference classLoaderReference){
        this(new JavaClassConverter(classLoaderReference));
    }

    /**
     * @deprecated As of 1.4.5 use {@link #JavaMethodConverter(ClassLoaderReference)}
     */
    @Deprecated
    public JavaMethodConverter(ClassLoader classLoader){
        this(new ClassLoaderReference(classLoader));
    }

    /**
     * Construct a JavaMethodConverter.
     * 
     * @param javaClassConverter
     *            the converter to use
     * @since 1.4.5
     */
    protected JavaMethodConverter(SingleValueConverter javaClassConverter){
        this.javaClassConverter = javaClassConverter;
    }

    @Override
    public boolean canConvert(Class type){
        return type == Method.class || type == Constructor.class;
    }

    @Override
    public void marshal(Object source,HierarchicalStreamWriter writer,MarshallingContext context){
        if (source instanceof Method){
            Method method = (Method) source;
            String declaringClassName = javaClassConverter.toString(method.getDeclaringClass());
            marshalMethod(writer, declaringClassName, method.getName(), method.getParameterTypes());
        }else{
            Constructor method = (Constructor) source;
            String declaringClassName = javaClassConverter.toString(method.getDeclaringClass());
            marshalMethod(writer, declaringClassName, null, method.getParameterTypes());
        }
    }

    private void marshalMethod(HierarchicalStreamWriter writer,String declaringClassName,String methodName,Class[] parameterTypes){

        writer.startNode("class");
        writer.setValue(declaringClassName);
        writer.endNode();

        if (methodName != null){
            // it's a method and not a ctor
            writer.startNode("name");
            writer.setValue(methodName);
            writer.endNode();
        }

        writer.startNode("parameter-types");
        for (Class parameterType : parameterTypes){
            writer.startNode("class");
            writer.setValue(javaClassConverter.toString(parameterType));
            writer.endNode();
        }
        writer.endNode();
    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader,UnmarshallingContext context){
        try{
            boolean isMethodNotConstructor = context.getRequiredType().equals(Method.class);

            reader.moveDown();
            String declaringClassName = reader.getValue();
            Class declaringClass = (Class) javaClassConverter.fromString(declaringClassName);
            reader.moveUp();

            String methodName = null;
            if (isMethodNotConstructor){
                reader.moveDown();
                methodName = reader.getValue();
                reader.moveUp();
            }

            reader.moveDown();
            List parameterTypeList = new ArrayList();
            while (reader.hasMoreChildren()){
                reader.moveDown();
                String parameterTypeName = reader.getValue();
                parameterTypeList.add(javaClassConverter.fromString(parameterTypeName));
                reader.moveUp();
            }
            Class[] parameterTypes = (Class[]) parameterTypeList.toArray(new Class[parameterTypeList.size()]);
            reader.moveUp();

            if (isMethodNotConstructor){
                return declaringClass.getDeclaredMethod(methodName, parameterTypes);
            }else{
                return declaringClass.getDeclaredConstructor(parameterTypes);
            }
        }catch (NoSuchMethodException e){
            throw new ConversionException(e);
        }
    }
}
