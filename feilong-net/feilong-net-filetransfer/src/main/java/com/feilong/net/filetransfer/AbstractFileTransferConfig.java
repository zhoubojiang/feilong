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
package com.feilong.net.filetransfer;

import com.feilong.json.SensitiveWords;

/**
 * 抽象的文件传输配置.
 *
 * @author <a href="https://github.com/ifeilong/feilong">feilong</a>
 * @since 2.1.0
 */
public abstract class AbstractFileTransferConfig{

    /** 地址. */
    private String  hostName;

    /** 端口号. */
    private Integer port;

    //---------------------------------------------------------------

    /** 用户名. */
    private String  userName;

    /** 密码. */
    @SensitiveWords
    private String  password;

    //---------------------------------------------------------------

    /**
     * 获得 host name.
     *
     * @return the hostName
     */
    public String getHostName(){
        return hostName;
    }

    /**
     * 设置 host name.
     *
     * @param hostName
     *            the hostName to set
     */
    public void setHostName(String hostName){
        this.hostName = hostName;
    }

    /**
     * 获得 用户名.
     *
     * @return the userName
     */
    public String getUserName(){
        return userName;
    }

    /**
     * 设置 用户名.
     *
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName){
        this.userName = userName;
    }

    /**
     * 获得 密码.
     *
     * @return the password
     */
    public String getPassword(){
        return password;
    }

    /**
     * 设置 密码.
     *
     * @param password
     *            the password to set
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * 获得 端口号.
     *
     * @return the 端口号
     */
    public Integer getPort(){
        return port;
    }

    /**
     * 设置 端口号.
     *
     * @param port
     *            the new 端口号
     */
    public void setPort(Integer port){
        this.port = port;
    }
}
