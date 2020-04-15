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
package com.feilong.tools.compress;

import static com.feilong.core.date.DateExtensionUtil.formatDuration;
import static com.feilong.core.date.DateUtil.now;
import static com.feilong.tools.slf4j.Slf4jUtil.format;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.core.UncheckedIOException;

/**
 * 抽象的压缩.
 *
 * @author <a href="http://feitianbenyue.iteye.com/">feilong</a>
 * @since 2.1.0
 */
public abstract class AbstractZipHandler implements ZipHandler{

    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractZipHandler.class);

    /*
     * (non-Javadoc)
     * 
     * @see com.feilong.tools.compress.UnzipManager#unZip(java.lang.String, java.lang.String)
     */
    @Override
    public void zip(String tobeZipFilePath,String outputZipPath){
        Validate.notBlank(tobeZipFilePath, "tobeZipFilePath can't be blank!");
        Validate.notBlank(outputZipPath, "outputZipPath can't be blank!");

        //---------------------------------------------------------------
        Date beginDate = now();
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("begin zip:[{}] to outputZipPath:[{}]", tobeZipFilePath, outputZipPath);
        }

        //---------------------------------------------------------------

        try{
            handle(tobeZipFilePath, outputZipPath);
        }catch (IOException e){
            String message = format("tobeZipFilePath:[{}],outputZipPath:[{}]", tobeZipFilePath, outputZipPath);
            throw new UncheckedIOException(message, e);
        }

        //---------------------------------------------------------------
        if (LOGGER.isInfoEnabled()){
            LOGGER.info("use time:[{}],end zip:[{}],outputZipPath:[{}]", formatDuration(beginDate), tobeZipFilePath, outputZipPath);
        }
    }

    //---------------------------------------------------------------

    /**
     * 处理.
     *
     * @param tobeZipFilePath
     *            the un zip file path
     * @param outputZipPath
     *            the output directory
     */
    protected abstract void handle(String tobeZipFilePath,String outputZipPath) throws IOException;

    //---------------------------------------------------------------

    //    /**
    //     * Handler.
    //     *
    //     * @param zipEntryName
    //     *            the zip entry name
    //     * @param inputStream
    //     *            the input stream
    //     * @param outputDirectory
    //     *            the output directory
    //     * @see com.feilong.io.IOWriteUtil#write(InputStream, String, String)
    //     */
    //    protected static void write(String zipEntryName,InputStream inputStream,String outputDirectory){
    //        IOWriteUtil.write(inputStream, outputDirectory, zipEntryName);
    //
    //        if (LOGGER.isDebugEnabled()){
    //            LOGGER.debug("unzip [{}] to [{}]", zipEntryName, outputDirectory + File.separator + zipEntryName);
    //        }
    //    }

}
