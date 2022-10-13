package com.example.finalproject.ConversationLogic;

import com.example.finalproject.Firebase.Authentication;
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
        ArrayList<User> mUsers = Authentication.getInstance().getUsersRepository().getUsersList();

        for (int i = 0; i < mUsers.size(); i++) {
            mChattingUsers.add(new Conversation(i+"",mUsers.get(i)));
        }
    }
}
