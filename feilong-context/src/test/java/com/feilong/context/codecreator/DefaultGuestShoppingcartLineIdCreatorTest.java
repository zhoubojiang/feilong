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
package com.feilong.context.codecreator;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.feilong.context.codecreator.DefaultGuestShoppingcartLineIdCreator;
import com.feilong.context.codecreator.GuestShoppingcartLineIdCreator;

/**
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.10.6
 */
public class DefaultGuestShoppingcartLineIdCreatorTest{

    private static final Set<Long> SET = new HashSet<>();

    @Test
    public void testDefaultGuestShoppingcartLineIdCreatorTest(){
        GuestShoppingcartLineIdCreator guestShoppingcartLineIdCreator = new DefaultGuestShoppingcartLineIdCreator();

        for (int i = 0; i < 1000; ++i){
            long id = guestShoppingcartLineIdCreator.create();
            if (SET.contains(id)){
                throw new IllegalArgumentException("contains(" + i + " " + id + ")");
            }
            SET.add(id);
        }
    }
}
