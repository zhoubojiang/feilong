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
package com.feilong.excel;

import static java.util.Collections.emptyMap;

import org.junit.Test;

import com.feilong.lib.springframework.util.ResourceUtils;
import com.feilong.test.AbstractTest;

public class ExcelWriteUtilTest extends AbstractTest{

    private final String templateLocation        = ResourceUtils.CLASSPATH_URL_PREFIX + "销售数据/audit/export-template-sales-vlookup.xlsx";

    private final String sheetDefinitionLocation = ResourceUtils.CLASSPATH_URL_PREFIX + "销售数据/audit/sheets-definition.xml";

    @Test(expected = NullPointerException.class)
    public void testExcelWriteUtilTestNull(){
        ExcelWriteUtil.write(null, sheetDefinitionLocation, "test", emptyMap(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcelWriteUtilTestEmpty(){
        ExcelWriteUtil.write("", sheetDefinitionLocation, "test", emptyMap(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcelWriteUtilTestBlank(){
        ExcelWriteUtil.write(" ", sheetDefinitionLocation, "test", emptyMap(), null);
    }

    //---------------------------------------------------------------

    @Test(expected = NullPointerException.class)
    public void testExcelWriteUtilTestNull1(){
        ExcelWriteUtil.write(templateLocation, null, "test", emptyMap(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcelWriteUtilTestEmpty1(){
        ExcelWriteUtil.write(templateLocation, "", "test", emptyMap(), null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExcelWriteUtilTestBlank1(){
        ExcelWriteUtil.write(templateLocation, " ", "test", emptyMap(), null);
    }
}
