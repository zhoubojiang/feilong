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
package com.feilong.core.bean.beanutil;

import static com.feilong.core.date.DateUtil.now;

import org.junit.Test;

import com.feilong.core.bean.BeanOperationException;
import com.feilong.core.bean.BeanUtil;
import com.feilong.core.bean.beanutil.entity.AccessExceptionProperty;
import com.feilong.store.member.User;

public class CopyPropertiesExceptionLogTest{

    @Test
    public void testNoDateLocaleConverter(){
        User user = new User();
        user.setDate(now());

        User user2 = new User();

        //ConvertUtil.registerSimpleDateLocaleConverter(TO_STRING_STYLE)
        BeanUtil.copyProperties(user2, user, "date");
    }

    @Test(expected = BeanOperationException.class)
    public void testCopyProperties(){
        AccessExceptionProperty user = new AccessExceptionProperty();

        AccessExceptionProperty user2 = new AccessExceptionProperty();
        BeanUtil.copyProperties(user2, user);
    }
}
