package com.feilong.xml.xstream.annotation;

import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Response")
public class Response{

    @XStreamAlias("returncode")
    private int       resultCode;

    @XStreamAlias("time")
    private Timestamp time;

    @XStreamAlias("returnmessage")
    private String    resultMessage;

    @XStreamAlias("return_message")
    private String    result_Message;

    @XStreamAlias("status")
    private int       state;

    @Override
    public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}
