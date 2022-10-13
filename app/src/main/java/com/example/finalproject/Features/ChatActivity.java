package com.example.finalproject.Features;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.ChatLogic.MessageManager;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    ImageView chatProfileImage;
    ImageButton sendMessageBtn;
    TextView chatUserName;
    EditText messageEt;
    User chatPartner;
    String conversationId;
    RecyclerView myChatHistoryRecyclerView;

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        conversationId = intent.getStringExtra("CONVERSATION_ID");
        chatPartner = intent.getParcelableExtra("USER");

        setViews();
        setRecyclerView();

    }

    private void setViews() {
        chatProfileImage = findViewById(R.id.userProfileImageInChat);
        sendMessageBtn = findViewById(R.id.sendMessageBtn);
        chatUserName = findViewById(R.id.chatDisplayUserName);
        messageEt = findViewById(R.id.messageEditText);

        chatUserName.setText(chatPartner.getUserFullName());
        /*Glide.with(this)
                .load(chatPartner.getPhotoPath())
                .circleCrop()
                .into(chatProfileImage);*/

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendMessage(new Message());
            }
        });
    }

    private void setRecyclerView(){
        myChatHistoryRecyclerView = findViewById(R.id.chattingHistoryRecycleView);
        myChatHistoryRecyclerView.setHasFixedSize(true);
        myChatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myChatHistoryRecyclerView.setAdapter(new MessageManager().getMassageAdapter());
    }
}
