package com.example.finalproject.ConversationLogic;

import com.example.finalproject.Instance.Conversation;
import com.example.finalproject.Instance.User;

import java.util.ArrayList;
import java.util.List;

public class ConversationManager {

    private ConversationAdapter conversationAdapter;
    private List<Conversation> mChattingUsers;

    public ConversationManager(ConversationAdapter.ConversationAdapterListener iListener) {
        initListTemp();
        conversationAdapter = new ConversationAdapter(iListener, mChattingUsers);
    }

    public ConversationAdapter getConversationAdapter() {
        return conversationAdapter;
    }

    private void initListTemp(){
        mChattingUsers=new ArrayList<>();

        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
        mChattingUsers.add(new Conversation("123",new User("linoy@gamil.com", "12345678")));
    }
}
