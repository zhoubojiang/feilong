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
package com.feilong.core.lang.numberutiltest;

import org.junit.Test;

import com.feilong.core.lang.NumberUtil;

public class IsGatherThanOrEqualsTest{

    @Test(expected = NullPointerException.class)
    public void testIsGatherThanOrEqualsTestNull1(){
        NumberUtil.isGatherThanOrEquals(null, 1);
    }

    @Test(expected = NullPointerException.class)
    public void testIsGatherThanOrEqualsTestNull2(){
        NumberUtil.isGatherThanOrEquals(1, null);
    }

    @Test(expected = NullPointerException.class)
    public void testIsGatherThanOrEqualsTestNull3(){
        NumberUtil.isGatherThanOrEquals(null, null);
    }

}
