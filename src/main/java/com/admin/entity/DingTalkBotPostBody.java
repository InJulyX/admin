package com.admin.entity;

import lombok.Data;

@Data
public class DingTalkBotPostBody {
    private String msgtype;
    private DingTalkBotPostBodyText text;
    private String msgId;
    private String createAt;
    private String conversationType;
    private String conversationTitle;
    private String senderId;
    private String senderNick;
    private String senderCorpId;
    private String senderStaffId;
    private String chatbotUserId;
    private DingTalkBotPostBodyAtUsers[] atUsers;

    @Override
    public String toString() {
        return "DingTalkBotPostBody{" +
                "msgtype='" + msgtype + '\'' +
                ", text=" + text +
                ", msgId='" + msgId + '\'' +
                ", createAt='" + createAt + '\'' +
                ", conversationType='" + conversationType + '\'' +
                ", conversationTitle='" + conversationTitle + '\'' +
                ", senderId='" + senderId + '\'' +
                ", senderNick='" + senderNick + '\'' +
                ", senderCorpId='" + senderCorpId + '\'' +
                ", senderStaffId='" + senderStaffId + '\'' +
                ", chatbotUserId='" + chatbotUserId + '\'' +
                ", atUsers=" + atUsers +
                '}';
    }
}
