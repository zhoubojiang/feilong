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
package com.feilong.json.processor;

import static com.feilong.core.util.MapUtil.newHashMap;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

import java.util.Map;

import org.junit.Test;

import com.feilong.json.JavaToJsonConfig;
import com.feilong.json.JsonUtil;
import com.feilong.lib.json.processors.JsonValueProcessor;
import com.feilong.store.member.User;
import com.feilong.test.AbstractTest;

public class SensitiveWordsJsonValueProcessorTest extends AbstractTest{

    @Test
    public void test(){
        User user = new User("feilong1", 24);
        user.setPassword("123456");

        assertThat(
                        JsonUtil.format(user, build()), //
                        allOf(//
                                        containsString("\"password\": \"******\""),
                                        containsString("\"age\": \"******\"")

                        ));
    }

    private static JavaToJsonConfig build(){
        Map<String, JsonValueProcessor> propertyNameAndJsonValueProcessorMap = newHashMap();
        propertyNameAndJsonValueProcessorMap.put("password", SensitiveWordsJsonValueProcessor.INSTANCE);
        propertyNameAndJsonValueProcessorMap.put("age", SensitiveWordsJsonValueProcessor.INSTANCE);

        JavaToJsonConfig jsonFormatConfig = new JavaToJsonConfig();
        jsonFormatConfig.setPropertyNameAndJsonValueProcessorMap(propertyNameAndJsonValueProcessorMap);
        jsonFormatConfig.setIncludes("name", "age", "password");
        return jsonFormatConfig;
    }

}
