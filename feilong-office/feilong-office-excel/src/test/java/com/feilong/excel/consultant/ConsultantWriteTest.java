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
package com.feilong.excel.consultant;

import static com.feilong.core.date.DateUtil.now;
import static com.feilong.core.util.CollectionsUtil.newArrayList;

import java.util.List;

import org.junit.Test;

import com.feilong.excel.AbstractLoxiaWriteTest;

public class ConsultantWriteTest extends AbstractLoxiaWriteTest{

    int count = 200;

    @Test
    @SuppressWarnings("squid:S2699") //Tests should include assertions //https://stackoverflow.com/questions/10971968/turning-sonar-off-for-certain-code
    public void test(){
        String templateFileName = "consultant/consultant-list-export.xlsx";
        String configurations = "excel/consultant/feilong-sheets-Consultant.xml";

        String sheetName = "consultantExport";

        //---------------------------------------------------------------
        List<ConsultantCommand> list = buildList();
        build(templateFileName, configurations, sheetName, "consultantList", list);
    }

    private List<ConsultantCommand> buildList(){
        List<ConsultantCommand> list = newArrayList();

        for (int i = 1; i <= count; ++i){
            ConsultantCommand command = new ConsultantCommand();

            command.setItemId((long) i);

            command.setItemCode("FK00" + i);
            command.setItemName("羽绒服FK00" + i);
            command.setResponseTime(now());
            command.setContent("你好");

            list.add(command);
        }
        return list;
    }
}
