/*
 * Copyright (C) 2008 feilong
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.feilong.core.util.predicate.beanpredicateutil;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.feilong.core.util.predicate.BeanPredicateUtil;
import com.feilong.lib.collection4.functors.ComparatorPredicate.Criterion;
import com.feilong.store.member.User;

public class ComparatorPredicateTest{

    //---------------------------------------------------------------
    @Test
    public void testComparatorPredicate1(){
        User user = new User(2L);
        assertEquals(false, BeanPredicateUtil.comparatorPredicate("id", 5L, Criterion.LESS).evaluate(user));
    }

    //---------------------------------------------------------------------------

    /**
     * Test equal predicate null property name.
     */
    @Test(expected = NullPointerException.class)
    public void testComparatorPredicateNullPropertyName(){
        BeanPredicateUtil.comparatorPredicate((String) null, 5, Criterion.LESS);
    }

    /**
     * Test equal predicate empty property name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testComparatorPredicateEmptyPropertyName(){
        BeanPredicateUtil.comparatorPredicate("", 5, Criterion.LESS);
    }

    /**
     * Test equal predicate blank property name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testComparatorPredicateBlankPropertyName(){
        BeanPredicateUtil.comparatorPredicate("", 5, Criterion.LESS);
    }
}
