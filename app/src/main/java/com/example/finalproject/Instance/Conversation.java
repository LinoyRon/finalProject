package com.example.finalproject.Instance;

public class Conversation {
    String conversationId;
    User oppositeUser;

    public Conversation() {
    }

    public Conversation(String conversationId, User oppositeUser) {
        this.conversationId = conversationId;
        this.oppositeUser = oppositeUser;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public User getOppositeUser() {
        return oppositeUser;
    }

    public void setOppositeUser(User oppositeUser) {
        this.oppositeUser = oppositeUser;
    }
}
