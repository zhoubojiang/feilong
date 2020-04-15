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
package com.feilong.formatter;

import static com.feilong.core.bean.ConvertUtil.toArray;
import static com.feilong.formatter.FormatterUtil.formatToSimpleTable;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.store.member.User;

/**
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 1.8.2
 */
public class FormatterUtilWithArrayTest{

    private static final Logger LOGGER = LoggerFactory.getLogger(FormatterUtilWithArrayTest.class);

    @Test
    @SuppressWarnings("static-method")
    public final void test(){
        User user = new User();
        user.setNickNames(toArray("jinxin", "feilong"));
        LOGGER.debug(formatToSimpleTable(user));
    }

}
