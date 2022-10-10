package com.example.finalproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.Instance.Message;
import com.example.finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ChatFragment extends Fragment {

    View mView;
    ImageView chatProfileImage;
    ImageButton sendMessageBtn;
    TextView chatUserName;
    EditText messageEt;

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
       mView = inflater.inflate(R.layout.fragment_chat, container, false);

       setViews();

       return mView;
    }

    private void setViews() {
        chatProfileImage = mView.findViewById(R.id.userProfileImageInChat);
        sendMessageBtn = mView.findViewById(R.id.sendMessageBtn);
        chatUserName = mView.findViewById(R.id.chatUserName_background);
        messageEt = mView.findViewById(R.id.messageEditText);
    }

    private void sendMessage(String iSender, String iReceiver, String iMessage){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Chats").push().setValue(new Message(iSender,iReceiver,iMessage));
    }
}
