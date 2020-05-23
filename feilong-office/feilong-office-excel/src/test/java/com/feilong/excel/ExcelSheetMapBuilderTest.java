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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;

import com.feilong.excel.definition.ExcelSheet;
import com.feilong.test.AbstractTest;

public class ExcelSheetMapBuilderTest extends AbstractTest{

    @Test
    public void test(){
        Map<String, ExcelSheet> map = ExcelSheetMapBuilder.build("excel/consultant/sheets-definition.xml");
        assertTrue(map.size() == 1);

        assertThat(map, allOf(hasKey("consultantExport")));

    }
}
