/*
 * Copyright (C) 2011, 2013, 2014 XStream Committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 * 
 * Created on 12. July 2011 by Joerg Schaible
 */
package com.feilong.lib.xstream.core.util;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * A HashMap implementation with weak references values and by default for the key. When the
 * value is garbage collected, the key will also vanish from the map.
 * 
 * @author J&ouml;rg Schaible
 * @since 1.4
 */
public class WeakCache extends AbstractMap{

    private final Map map;

    /**
     * Construct a WeakCache with weak keys.
     * 
     * <p>
     * Note, that the internally used WeakHashMap is <b>not</b> thread-safe.
     * </p>
     * 
     * @param map
     *            the map to use
     * @since 1.4
     */
    public WeakCache(){
        this(new WeakHashMap());
    }

    /**
     * Construct a WeakCache.
     * 
     * @param map
     *            the map to use
     * @since 1.4
     */
    public WeakCache(Map map){
        this.map = map;
    }

    @Override
    public Object get(Object key){
        Reference reference = (Reference) map.get(key);
        return reference != null ? reference.get() : null;
    }

    @Override
    public Object put(Object key,Object value){
        Reference ref = (Reference) map.put(key, createReference(value));
        return ref == null ? null : ref.get();
    }

    @Override
    public Object remove(Object key){
        Reference ref = (Reference) map.remove(key);
        return ref == null ? null : ref.get();
    }

    protected Reference createReference(Object value){
        return new WeakReference(value);
    }

    @Override
    public boolean containsValue(final Object value){
        Boolean result = (Boolean) iterate(element -> element.equals(value) ? Boolean.TRUE : null, 0);
        return result == Boolean.TRUE;
    }

    @Override
    public int size(){
        if (map.size() == 0){
            return 0;
        }
        final int i[] = new int[1];
        i[0] = 0;
        iterate(element -> {
            ++i[0];
            return null;
        }, 0);
        return i[0];
    }

    @Override
    public Collection values(){
        final Collection collection = new ArrayList();
        if (map.size() != 0){
            iterate(element -> {
                collection.add(element);
                return null;
            }, 0);
        }
        return collection;
    }

    @Override
    public Set entrySet(){
        final Set set = new HashSet();
        if (map.size() != 0){
            iterate(element -> {
                final Map.Entry entry = (Map.Entry) element;
                set.add(new Map.Entry(){

                    @Override
                    public Object getKey(){
                        return entry.getKey();
                    }

                    @Override
                    public Object getValue(){
                        return ((Reference) entry.getValue()).get();
                    }

                    @Override
                    public Object setValue(Object value){
                        Reference reference = (Reference) entry.setValue(createReference(value));
                        return reference != null ? reference.get() : null;
                    }

                });
                return null;
            }, 2);
        }
        return set;
    }

    private Object iterate(Visitor visitor,int type){
        Object result = null;
        for (Iterator iter = map.entrySet().iterator(); result == null && iter.hasNext();){
            Map.Entry entry = (Map.Entry) iter.next();
            Reference reference = (Reference) entry.getValue();
            Object element = reference.get();
            if (element == null){
                iter.remove();
                continue;
            }
            switch (type) {
                case 0:
                    result = visitor.visit(element);
                    break;
                case 1:
                    result = visitor.visit(entry.getKey());
                    break;
                case 2:
                    result = visitor.visit(entry);
                    break;
            }

        }
        return result;
    }

    private interface Visitor{

        Object visit(Object element);
    }

    @Override
    public boolean containsKey(Object key){
        return map.containsKey(key);
    }

    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public Set keySet(){
        return map.keySet();
    }

    @Override
    public boolean equals(Object o){
        return map.equals(o);
    }

    @Override
    public int hashCode(){
        return map.hashCode();
    }

    @Override
    public String toString(){
        return map.toString();
    }
}
