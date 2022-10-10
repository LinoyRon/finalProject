package com.example.finalproject.ChatLogic;

import com.example.finalproject.ConversationLogic.ConversationAdapter;
import com.example.finalproject.Instance.User;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {

    private static ChatManager mChatManager = new ChatManager();
    private ConversationAdapter conversationAdapter;
    private List<User> m_ChattingUsers;

    public static ChatManager getInstance(){ return mChatManager;}

    public ChatManager() {
        initListTemp();
        conversationAdapter = new ConversationAdapter(m_ChattingUsers);
    }

    public ConversationAdapter getConversationAdapter() {
        return conversationAdapter;
    }

    private void initListTemp(){
        m_ChattingUsers=new ArrayList<User>();

        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
        m_ChattingUsers.add(new User("linoy@gamil.com", "12345678"));
    }
}
