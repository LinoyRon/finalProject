package com.example.finalproject.ChatLogic;

import com.example.finalproject.Firebase.MessagesRepository;
import com.example.finalproject.Instance.Message;

import java.util.List;

public class MessageManager {

    private MassageAdapter mMessageAdapter;
    private List<Message> mChattingHistory;

    public MessageManager() {
        MessagesRepository.getInstance().getMessageHistoryList();
        mMessageAdapter = new MassageAdapter(mChattingHistory);
    }

    public MassageAdapter getMassageAdapter() { return mMessageAdapter; }

    public List<Message> getChattingHistory() {
        mMessageAdapter.notifyDataSetChanged();
        return mChattingHistory = MessagesRepository.getInstance().getMessageHistoryList();
    }
}
