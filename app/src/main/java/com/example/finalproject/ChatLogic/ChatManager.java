package com.example.finalproject.ChatLogic;

import com.example.finalproject.ConversationLogic.ConversationAdapter;
import com.example.finalproject.Instance.User;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {

    private static ChatManager mChatManager = new ChatManager();
    private ChatAdapter mChatAdapter;
    private List<User> mChattingHistory;

    public static ChatManager getInstance(){ return mChatManager;}

    public ChatManager() {
        initListTemp();
        mChatAdapter = new ChatAdapter(mChattingHistory);
    }

    public ChatAdapter getConversationAdapter() {
        return mChatAdapter;
    }

    private void initListTemp(){
        mChattingHistory=new ArrayList<>();

        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
        mChattingHistory.add(new User("linoy@gamil.com", "12345678"));
    }
}
