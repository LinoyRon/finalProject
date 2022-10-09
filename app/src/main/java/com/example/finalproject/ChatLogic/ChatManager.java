package com.example.finalproject.ChatLogic;

import com.example.finalproject.Instance.User;

import java.util.List;

public class ChatManager {

    private ConversationAdapter conversationAdapter;
    private List<User> m_ChattingUsers;

    public ChatManager() {
        conversationAdapter = new ConversationAdapter(m_ChattingUsers);
    }
}
