package com.example.finalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.finalproject.Instance.Message;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        conversationId = intent.getStringExtra("CONVERSATION_ID");
        chatPartner = intent.getParcelableExtra("USER");

        setViews();
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
    }

    private void sendMessage(String iSender, String iReceiver, String iMessage){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Chats").push().setValue(new Message(iSender,iReceiver,iMessage));
    }
}
