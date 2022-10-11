package com.example.finalproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Activities.ChatActivity;
import com.example.finalproject.ConversationLogic.ConversationAdapter;
import com.example.finalproject.ConversationLogic.ConversationManager;
import com.example.finalproject.Instance.Conversation;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;

import java.io.Serializable;

public class ConversationFragment extends Fragment {

    View myView;
    private RecyclerView myConverationRecyclerView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_conversation, container, false);
        setRecyclerView();
        return myView;
    }

    private void setRecyclerView(){
        myConverationRecyclerView = myView.findViewById(R.id.chatRecyclerview);
        myConverationRecyclerView.setHasFixedSize(true);
        myConverationRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        myConverationRecyclerView.setAdapter(new ConversationManager(getConversationAdapterListener()).getConversationAdapter());
    }

    private ConversationAdapter.ConversationAdapterListener getConversationAdapterListener() {
        return new ConversationAdapter.ConversationAdapterListener() {
            @Override
            public void onConversationClick(Conversation iConversation) {
                openChatActivity(iConversation.getConversationId(), iConversation.getChatPartner());
            }
        };
    }

    private void openChatActivity(String iChatId, User iUser) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("CONVERSATION_ID", iChatId);
        intent.putExtra("USER", iUser);
        startActivity(intent);
    }
}
