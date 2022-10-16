package com.example.finalproject.Instance;

import com.example.finalproject.Firebase.Authentication;

public class Conversation {
    String ConversationId;
    Message lastMessage;
    User CurrentUser, ChatPartner;

    public Conversation() {
    }

    public Conversation(String ConversationId, User ChatPartner) {

        this.ChatPartner = ChatPartner;
        CurrentUser = Authentication.getInstance().getLoggedInUser();
    }

    public Message getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(Message lastMessage) {
        this.lastMessage = lastMessage;
    }

    public User getChatPartner() {
        return ChatPartner;
    }

    public void setChatPartner(User ChatPartner) {
        this.ChatPartner = ChatPartner;
    }

    public User getCurrentUser() {
        return CurrentUser;
    }

    public void setCurrentUser(User CurrentUser) {
        this.CurrentUser = CurrentUser;
    }

    public String getConversationId() {
        return ConversationId;
    }

    public void setConversationId(String conversationId) {
        ConversationId = conversationId;
    }
}
