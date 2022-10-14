package com.example.finalproject.Instance;

public class Message {
    String MessageId, MessageContent;
    User Sender, Receiver;

    public Message() {
    }

    public Message(User sender, User receiver, String messageContent) {
        MessageContent = messageContent.trim();
        Sender = sender;
        Receiver = receiver;
    }

    public String getConversationId() {
        return MessageId;
    }

    public void setConversationId(String conversationId) {
        MessageId = conversationId;
    }

    public String getMessageContent() {
        return MessageContent;
    }

    public void setMessageContent(String messageContent) {
        MessageContent = messageContent;
    }

    public User getSender() {
        return Sender;
    }

    public void setSender(User sender) {
        Sender = sender;
    }

    public User getReceiver() {
        return Receiver;
    }

    public void setReceiver(User receiver) {
        Receiver = receiver;
    }

    /* public Boolean IsSent() {
        return Objects.equals(getReceiver().getID(), Authentication.getInstance().getLoggedInUser().getID());
    }*/
}
