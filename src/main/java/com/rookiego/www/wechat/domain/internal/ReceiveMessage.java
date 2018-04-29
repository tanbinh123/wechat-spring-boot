package com.rookiego.www.wechat.domain.internal;

import com.alibaba.fastjson.annotation.JSONField;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("receiveMessage")
public class ReceiveMessage {
    private String msgId;
    private String msgType;
    private String msgContent;
    private String openId;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
