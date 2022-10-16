package com.example.finalproject.Messages;

import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.User;

import java.util.List;

public class MessageManager {

    private MassageAdapter mMessageAdapter;
    private List<Message> mChatHistory;
    MessagesRepository.NotifyAdapterListener listener;

    public MessageManager(User iChatPartner) {
        mChatHistory = MessagesRepository.getInstance().getChatHistory(iChatPartner);
        mMessageAdapter = new MassageAdapter(mChatHistory);

        listener = new MessagesRepository.NotifyAdapterListener() {
            @Override
            public void addMessage(Message iAddMessage) {
                mChatHistory.add(iAddMessage);
                mMessageAdapter.notifyDataSetChanged();
            }
        };

        MessagesRepository.getInstance().setListener(listener);
    }

    public MassageAdapter getMassageAdapter() { return mMessageAdapter; }
}
