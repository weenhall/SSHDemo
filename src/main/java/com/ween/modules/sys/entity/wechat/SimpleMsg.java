package com.ween.modules.sys.entity.wechat;

/**
 * Created by wen on 2017/7/20.
 */
public class SimpleMsg {
    private String ToUserName;//开发者微信号
    private String FromUserName;//发送方帐号
    private long CreateTime;//消息创建时间 （整型）
    private String MsgType;//text
    private String Content;//文本消息内容
    private long MsgId;//消息id，64位整型

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public long getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(long createTime) {
        CreateTime = createTime;
    }

    public String getMsgType() {
        return MsgType;
    }

    public void setMsgType(String msgType) {
        MsgType = msgType;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public long getMsgId() {
        return MsgId;
    }

    public void setMsgId(long msgId) {
        MsgId = msgId;
    }
}
