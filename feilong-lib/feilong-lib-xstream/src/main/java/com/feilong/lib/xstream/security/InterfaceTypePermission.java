/*
 * Copyright (C) 2014 XStream Committers.
 * All rights reserved.
 *
 * Created on 27. January 2014 by Joerg Schaible
 */
package com.feilong.lib.xstream.security;

/**
 * Permission for any interface type.
 * 
 * @author J&ouml;rg Schaible
 * @since 1.4.7
 */
public class InterfaceTypePermission implements TypePermission{

    /**
     * @since 1.4.7
     */
    public static final TypePermission INTERFACES = new InterfaceTypePermission();

    @Override
    public boolean allows(Class type){
        return type != null && type.isInterface();
    }

    @Override
    public int hashCode(){
        return 31;
    }

    @Override
    public boolean equals(Object obj){
        return obj != null && obj.getClass() == InterfaceTypePermission.class;
    }

}
