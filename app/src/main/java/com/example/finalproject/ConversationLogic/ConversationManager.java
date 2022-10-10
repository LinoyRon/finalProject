package com.example.finalproject.ConversationLogic;

import com.example.finalproject.Instance.User;

import java.util.ArrayList;
import java.util.List;

public class ConversationManager {

    private static ConversationManager mChatManager = new ConversationManager();
    private ConversationAdapter conversationAdapter;
    private List<User> m_ChattingUsers;

    public static ConversationManager getInstance(){ return mChatManager;}

    public ConversationManager() {
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
