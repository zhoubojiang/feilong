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
package com.feilong.excel.reader;

import org.apache.poi.ss.usermodel.Workbook;

import com.feilong.excel.definition.ExcelBlock;
import com.feilong.excel.definition.ExcelSheet;
import com.feilong.lib.excel.ognl.OgnlStack;

/**
 * The Class SheetReader.
 */
public class SheetReader{

    /** The Constant STATUS_DATA_COLLECTION_ERROR. */
    private static final int STATUS_DATA_COLLECTION_ERROR = 10;

    //---------------------------------------------------------------

    /** Don't let anyone instantiate this class. */
    private SheetReader(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    //---------------------------------------------------------------

    /**
     * Read sheet.
     *
     * @param workbook
     *            the wb
     * @param sheetNo
     *            the sheet no
     * @param sheetDefinition
     *            the sheet definition
     * @param ognlStack
     *            the stack
     * @param readStatus
     *            the read status
     * @param skipErrors
     *            the skip errors
     */
    public static void readSheet(
                    Workbook workbook,
                    int sheetNo,
                    ExcelSheet sheetDefinition,
                    OgnlStack ognlStack,
                    ReadStatus readStatus,
                    boolean skipErrors){
        //In Read Operation only the first loopBlock will be read
        int loopBlock = 0;

        int status = readStatus.getStatus();

        for (ExcelBlock excelBlock : sheetDefinition.getExcelBlocks()){
            if (((skipErrors && status == STATUS_DATA_COLLECTION_ERROR) || status == ReadStatus.STATUS_SUCCESS)
                            && (loopBlock < 1 || !excelBlock.isLoop())){
                if (excelBlock.isLoop()){
                    loopBlock++;
                    BlockReader.readLoopBlock(workbook, sheetNo, excelBlock, ognlStack, readStatus);
                }else{
                    BlockReader.readSimpleBlock(workbook, sheetNo, excelBlock, ognlStack, readStatus);
                }
            }
        }
    }
}
