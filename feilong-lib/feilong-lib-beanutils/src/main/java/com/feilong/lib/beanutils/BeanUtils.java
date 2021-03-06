/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.feilong.lib.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * <p>
 * Utility methods for populating JavaBeans properties via reflection.
 * </p>
 *
 * <p>
 * The implementations are provided by {@link BeanUtilsBean}.
 * These static utility methods use the default instance.
 * More sophisticated behaviour can be provided by using a <code>BeanUtilsBean</code> instance.
 * </p>
 *
 * @version $Id$
 * @see BeanUtilsBean
 */

public class BeanUtils{

    // --------------------------------------------------------- Class Methods

    /**
     * <p>
     * Clone a bean based on the available property getters and setters,
     * even if the bean class itself does not implement Cloneable.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @param bean
     *            Bean to be cloned
     * @return the cloned bean
     *
     * @throws IllegalAccessException
     *             if the caller does not have
     *             access to the property accessor method
     * @throws InstantiationException
     *             if a new instance of the bean's
     *             class cannot be instantiated
     * @throws InvocationTargetException
     *             if the property accessor method
     *             throws an exception
     * @throws NoSuchMethodException
     *             if an accessor method for this
     *             property cannot be found
     * @see BeanUtilsBean#cloneBean
     */
    public static Object cloneBean(final Object bean)
                    throws IllegalAccessException,InstantiationException,InvocationTargetException,NoSuchMethodException{
        return BeanUtilsBean.getInstance().cloneBean(bean);
    }

    /**
     * <p>
     * Copy property values from the origin bean to the destination bean
     * for all cases where the property names are the same.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @param dest
     *            Destination bean whose properties are modified
     * @param orig
     *            Origin bean whose properties are retrieved
     *
     * @throws IllegalAccessException
     *             if the caller does not have
     *             access to the property accessor method
     * @throws IllegalArgumentException
     *             if the <code>dest</code> or
     *             <code>orig</code> argument is null or if the <code>dest</code>
     *             property type is different from the source type and the relevant
     *             converter has not been registered.
     * @throws InvocationTargetException
     *             if the property accessor method
     *             throws an exception
     * @see BeanUtilsBean#copyProperties
     */
    public static void copyProperties(final Object dest,final Object orig) throws IllegalAccessException,InvocationTargetException{
        BeanUtilsBean.getInstance().copyProperties(dest, orig);
    }

    /**
     * <p>
     * Copy the specified property value to the specified destination bean,
     * performing any type conversion that is required.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @param bean
     *            Bean on which setting is to be performed
     * @param name
     *            Property name (can be nested/indexed/mapped/combo)
     * @param value
     *            Value to be set
     *
     * @throws IllegalAccessException
     *             if the caller does not have
     *             access to the property accessor method
     * @throws InvocationTargetException
     *             if the property accessor method
     *             throws an exception
     * @see BeanUtilsBean#copyProperty
     */
    public static void copyProperty(final Object bean,final String name,final Object value)
                    throws IllegalAccessException,InvocationTargetException{
        BeanUtilsBean.getInstance().copyProperty(bean, name, value);
    }

    /**
     * <p>
     * Return the entire set of properties for which the specified bean
     * provides a read method.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @param bean
     *            Bean whose properties are to be extracted
     * @return Map of property descriptors
     *
     * @throws IllegalAccessException
     *             if the caller does not have
     *             access to the property accessor method
     * @throws InvocationTargetException
     *             if the property accessor method
     *             throws an exception
     * @throws NoSuchMethodException
     *             if an accessor method for this
     *             property cannot be found
     * @see BeanUtilsBean#describe
     */
    public static Map<String, String> describe(final Object bean)
                    throws IllegalAccessException,InvocationTargetException,NoSuchMethodException{
        return BeanUtilsBean.getInstance().describe(bean);
    }

    /**
     * <p>
     * Return the value of the specified property of the specified bean,
     * no matter which property reference format is used, as a String.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @param bean
     *            Bean whose property is to be extracted
     * @param name
     *            Possibly indexed and/or nested name of the property
     *            to be extracted
     * @return The property's value, converted to a String
     *
     * @throws IllegalAccessException
     *             if the caller does not have
     *             access to the property accessor method
     * @throws InvocationTargetException
     *             if the property accessor method
     *             throws an exception
     * @throws NoSuchMethodException
     *             if an accessor method for this
     *             property cannot be found
     * @see BeanUtilsBean#getProperty
     */
    public static String getProperty(final Object bean,final String name)
                    throws IllegalAccessException,InvocationTargetException,NoSuchMethodException{
        return BeanUtilsBean.getInstance().getProperty(bean, name);
    }

    /**
     * <p>
     * Populate the JavaBeans properties of the specified bean, based on
     * the specified name/value pairs.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @param bean
     *            JavaBean whose properties are being populated
     * @param properties
     *            Map keyed by property name, with the
     *            corresponding (String or String[]) value(s) to be set
     *
     * @throws IllegalAccessException
     *             if the caller does not have
     *             access to the property accessor method
     * @throws InvocationTargetException
     *             if the property accessor method
     *             throws an exception
     * @see BeanUtilsBean#populate
     */
    public static void populate(final Object bean,final Map<String, ? extends Object> properties)
                    throws IllegalAccessException,InvocationTargetException{
        BeanUtilsBean.getInstance().populate(bean, properties);
    }

    /**
     * <p>
     * Set the specified property value, performing type conversions as
     * required to conform to the type of the destination property.
     * </p>
     *
     * <p>
     * For more details see <code>BeanUtilsBean</code>.
     * </p>
     *
     * @param bean
     *            Bean on which setting is to be performed
     * @param name
     *            Property name (can be nested/indexed/mapped/combo)
     * @param value
     *            Value to be set
     *
     * @throws IllegalAccessException
     *             if the caller does not have
     *             access to the property accessor method
     * @throws InvocationTargetException
     *             if the property accessor method
     *             throws an exception
     * @see BeanUtilsBean#setProperty
     */
    public static void setProperty(final Object bean,final String name,final Object value)
                    throws IllegalAccessException,InvocationTargetException{
        BeanUtilsBean.getInstance().setProperty(bean, name, value);
    }

    /**
     * Create a cache.
     * 
     * @param <K>
     *            the key type of the cache
     * @param <V>
     *            the value type of the cache
     * @return a new cache
     * @since 1.8.0
     */
    public static <K, V> Map<K, V> createCache(){
        return new WeakFastHashMap<>();
    }

    /**
     * Return whether a Map is fast
     * 
     * @param map
     *            The map
     * @return Whether it is fast or not.
     * @since 1.8.0
     */
    public static boolean getCacheFast(final Map<?, ?> map){
        if (map instanceof WeakFastHashMap){
            return ((WeakFastHashMap<?, ?>) map).getFast();
        }
        return false;
    }

    /**
     * Set whether fast on a Map
     * 
     * @param map
     *            The map
     * @param fast
     *            Whether it should be fast or not.
     * @since 1.8.0
     */
    public static void setCacheFast(final Map<?, ?> map,final boolean fast){
        if (map instanceof WeakFastHashMap){
            ((WeakFastHashMap<?, ?>) map).setFast(fast);
        }
    }
}
