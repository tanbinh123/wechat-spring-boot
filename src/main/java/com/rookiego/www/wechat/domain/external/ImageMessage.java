package com.rookiego.www.wechat.domain.external;

/**
 * <b>类名称:</b>ImageMessage<br/>
 * <b>类注释:</b>微信公众平台消息-图片消息实体<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月08日 14时41分27秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
public class ImageMessage {
    //开发者微信号
    private String toUserName;
    //发送方帐号
    private String fromUserName;
    //消息创建时间 （整型）
    private String createTime;
    //image
    private String msgType = "image";
    //图片链接（由系统生成）
    private String picUrl;
    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String mediaId;
    //消息id，64位整型
    private String msgId;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
