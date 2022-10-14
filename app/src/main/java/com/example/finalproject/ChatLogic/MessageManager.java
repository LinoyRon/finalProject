package com.example.finalproject.ChatLogic;

import com.example.finalproject.Firebase.MessagesRepository;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.User;

import java.util.ArrayList;
import java.util.List;

public class MessageManager {

    private MassageAdapter mMessageAdapter;
    private List<Message> mChattingHistory;

    public MessageManager() {
        initListTemp();
        mMessageAdapter = new MassageAdapter(mChattingHistory);
    }

    public MassageAdapter getMassageAdapter() { return mMessageAdapter; }

    private void initListTemp(){
        mChattingHistory=new ArrayList<>();

        mChattingHistory.add(new Message(new User("linoy@gmail.com","123"), new User("linoy@gmail.com","123"), "bla bla"));
        mChattingHistory.add(new Message(new User("linoy@gmail.com","123"), new User("linoy@gmail.com","123"), "bla bla"));
        mChattingHistory.add(new Message(new User("linoy@gmail.com","123"), new User("linoy@gmail.com","123"), "bla bla"));
        mChattingHistory.add(new Message(new User("linoy@gmail.com","123"), new User("linoy@gmail.com","123"), "bla bla"));
        mChattingHistory.add(new Message(new User("linoy@gmail.com","123"), new User("linoy@gmail.com","123"), "bla bla"));
        mChattingHistory.add(new Message(new User("linoy@gmail.com","123"), new User("linoy@gmail.com","123"), "bla bla"));
        mChattingHistory.add(new Message(new User("linoy@gmail.com","123"), new User("linoy@gmail.com","123"), "bla bla"));
        mChattingHistory.add(new Message(new User("linoy@gmail.com","123"), new User("linoy@gmail.com","123"), "bla bla"));

        mChattingHistory = MessagesRepository.getInstance().getMessageHistoryList();
    }

    public List<Message> getChattingHistory() {
        mMessageAdapter.notifyDataSetChanged();
        return mChattingHistory = MessagesRepository.getInstance().getMessageHistoryList();
    }
}
