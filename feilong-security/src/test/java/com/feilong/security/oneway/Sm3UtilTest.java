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
package com.feilong.security.oneway;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.feilong.security.AbstractSecurityTest;

public class Sm3UtilTest extends AbstractSecurityTest{

    @Test
    public void encodeFile() throws IOException{
        String encodeFile = Sm3Util.encodeFile(LOCATION);
        assertEquals("55e12e91650d2fec56ec74e1d3e4ddbfce2ef3a65890c2a19ecf88a307e76a23", encodeFile);
        //assertEquals(encodeFile, DigestUtils.sha256Hex(InputStreamUtil.getInputStream(location)));
    }

    @Test
    public void encode11(){
        String json = "{\"name\":\"Marydon\",\"website\":\"http://www.cnblogs.com/Marydon20170307\"}";
        LOGGER.debug(debugSecurityValue(Sm3Util.encode(json)));
    }

    @Test
    public void encode112(){
        String json = "你好";
        LOGGER.debug(debugSecurityValue(Sm3Util.encode(json)));
    }

    //---------------------------------------------------------------

    @Test(expected = NullPointerException.class)
    public void testSm3UtilTestNull(){
        Sm3Util.encodeFile(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSm3UtilTestEmpty(){
        Sm3Util.encodeFile("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSm3UtilTestBlank(){
        Sm3Util.encodeFile(" ");
    }
}
