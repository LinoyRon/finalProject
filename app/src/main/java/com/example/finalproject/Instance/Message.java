package com.example.finalproject.Instance;

public class Message {
    String conversationId;
    User mSender, mReceiver;
    String mMessage;
    Boolean isSent=false;

    public Message(User mSender, User mReceiver, String mMessage) {

        this.mSender = mSender;
        this.mReceiver = mReceiver;
        this.mMessage = mMessage;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public User getSender() {
        return mSender;
    }

    public void setSender(User mSender) {
        this.mSender = mSender;
    }

    public User getReceiver() {
        return mReceiver;
    }

    public void setReceiver(User mReceiver) {
        this.mReceiver = mReceiver;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public Boolean getIsSent() {
        return isSent;
    }

    public void setIsSent(Boolean sent) {
        isSent = sent;
    }
}
