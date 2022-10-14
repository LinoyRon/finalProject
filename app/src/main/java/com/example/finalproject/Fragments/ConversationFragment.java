package com.example.finalproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.ConversationLogic.ConversationAdapter;
import com.example.finalproject.ConversationLogic.ConversationManager;
import com.example.finalproject.Features.ChatActivity;
import com.example.finalproject.Instance.Conversation;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;

public class ConversationFragment extends Fragment {

    View myView;
    RecyclerView myConversationRecyclerView;
    EditText mSearch;

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
        setSearchView();


        return myView;
    }

    private void setSearchView() {
        mSearch=myView.findViewById(R.id.searchEditText);

    }

    private void setRecyclerView(){
        myConversationRecyclerView = myView.findViewById(R.id.chatRecyclerview);
        myConversationRecyclerView.setHasFixedSize(true);
        myConversationRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        myConversationRecyclerView.setAdapter(new ConversationManager(getConversationAdapterListener()).getConversationAdapter());
    }

    private ConversationAdapter.ConversationAdapterListener getConversationAdapterListener() {
        return new ConversationAdapter.ConversationAdapterListener() {
            @Override
            public void onConversationClick(Conversation iConversation) {
                openChatActivity(iConversation.getConversationId(),iConversation.getCurrentUser(),iConversation.getChatPartner());
            }
        };
    }

    private void openChatActivity(String iChatId, User iUser,User iPartnerChat) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("PARTNER_CHAT", iPartnerChat);
        intent.putExtra("USER", iUser);
        startActivity(intent);
    }
}
