package com.example.finalproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.ChatActivity;
import com.example.finalproject.Conversation.ConversationAdapter;
import com.example.finalproject.Conversation.ConversationManager;
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

        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                startSearch();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void startSearch() {

    }

    private void setRecyclerView(){
        myConversationRecyclerView = myView.findViewById(R.id.chatRecyclerview);
        myConversationRecyclerView.setHasFixedSize(true);
        myConversationRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        myConversationRecyclerView.setAdapter(new ConversationManager(getConversationAdapterListener()).getConversationAdapter());
    }

    private ConversationAdapter.ConversationAdapterListener getConversationAdapterListener() {
        return iConversation -> openChatActivity(iConversation.getConversationId(),iConversation.getCurrentUser(),iConversation.getChatPartner());
    }

    private void openChatActivity(String iChatId, User iUser,User iPartnerChat) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("PARTNER_CHAT", iPartnerChat);
        intent.putExtra("USER", iUser);
        startActivity(intent);
    }
}
