package com.example.finalproject.Instance;

public class Message {
    String conversationId;
    String mSender, mMessage, mReceiver;

    public Message(String mSender, String mMessage, String mReceiver) {
        this.mSender = mSender;
        this.mMessage = mMessage;
        this.mReceiver = mReceiver;
    }
}
