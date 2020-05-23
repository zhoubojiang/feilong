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
package com.feilong.lib.ognl;

import java.util.Arrays;

import com.feilong.core.DefaultRuntimeException;

import ognl.NullHandler;
import ognl.OgnlException;
import ognl.OgnlRuntime;

/**
 * 
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 3.0.0
 */
public class OgnlUtil{

    static void setNullHandler(){
        synchronized (OgnlStack.class){
            try{

                NullHandler nullHandler = OgnlRuntime.getNullHandler(Object.class);
                if (nullHandler == null || !(nullHandler instanceof InstantiatingNullHandler)){
                    OgnlRuntime.setNullHandler(Object.class, new InstantiatingNullHandler(nullHandler, Arrays.asList("java")));
                }

            }catch (OgnlException e){
                throw new DefaultRuntimeException(e);
            }
        }
    }

}
