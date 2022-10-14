package com.example.finalproject.Instance;

import com.example.finalproject.Firebase.Authentication;

public class Conversation {
    String ConversationId;
    User CurrentUser, ChatPartner;

    public Conversation() {
    }

    public Conversation(String ConversationId, User ChatPartner) {
        this.ConversationId = ConversationId;
        this.ChatPartner = ChatPartner;
        CurrentUser = Authentication.getInstance().getLoggedInUser();
    }

    public String getConversationId() {
        return ConversationId;
    }

    public void setConversationId(String ConversationId) {
        this.ConversationId = ConversationId;
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
}
