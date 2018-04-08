package com.rookiego.www.wechat.domain.wechat;

/**
 * <b>类名称:</b>VoiceMessage<br/>
 * <b>类注释:</b>微信公众平台消息-语音消息<br/>
 * <b>类描述:</b><br/>
 * <b>创建人:</b>rookie<br/>
 * <b>修改人:</b>rookie<br/>
 * <b>修改时间:</b>2018年04月08日 14时43分48秒<br/>
 * <b>修改备注:</b><br/>
 *
 * @version 1.0.0<br/>
 */
public class VoiceMessage {
    //开发者微信号
    private String toUserName;
    //发送方帐号
    private String fromUserName;
    //消息创建时间 （整型）
    private String createTime;
    //image
    private String msgType = "voice";
    //语音格式，如amr，speex等
    private String format;
    //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String mediaId;
    //语音识别结果，UTF8编码
    private String recognition;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getRecognition() {
        return recognition;
    }

    public void setRecognition(String recognition) {
        this.recognition = recognition;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
}
