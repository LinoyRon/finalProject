package com.example.finalproject.Instance;

public class Conversation {
    String mConversationId;
    User mChatPartner;

    public Conversation() {
    }

    public Conversation(String mConversationId, User mChatPartner) {
        this.mConversationId = mConversationId;
        this.mChatPartner = mChatPartner;
    }

    public String getConversationId() {
        return mConversationId;
    }

    public void setConversationId(String mConversationId) {
        this.mConversationId = mConversationId;
    }

    public User getChatPartner() {
        return mChatPartner;
    }

    public void setChatPartner(User mChatPartner) {
        this.mChatPartner = mChatPartner;
    }
}
