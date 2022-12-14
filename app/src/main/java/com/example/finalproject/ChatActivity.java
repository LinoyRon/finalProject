package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.Messages.MessageManager;
import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Messages.MessagesRepository;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    ImageView chatProfileImage;
    ImageButton sendMessageBtn;
    Button backBtn;
    TextView chatUserName;
    EditText messageEt;
    User chatPartner;
    String conversationId;
    RecyclerView myChatHistoryRecyclerView;
    Message mMessageToSend;

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        chatPartner = intent.getParcelableExtra("PARTNER_CHAT");

        setViews();
        setRecyclerView();
    }

    private void setViews() {
        chatProfileImage = findViewById(R.id.chatPartnerProfile);
        sendMessageBtn = findViewById(R.id.chatSendBtn);
        chatUserName = findViewById(R.id.chatPartnerName);
        messageEt = findViewById(R.id.chatCommentBar);
        backBtn = findViewById(R.id.backTo);

        chatUserName.setText(chatPartner.getFullName());
        Glide.with(this)
                .load(chatPartner.getPhotoPath())
                .circleCrop()
                .into(chatProfileImage);

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setRecyclerView(){
        myChatHistoryRecyclerView = findViewById(R.id.chatHistoryRecycler);
        myChatHistoryRecyclerView.setHasFixedSize(true);
        myChatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myChatHistoryRecyclerView.setAdapter(new MessageManager(chatPartner).getMassageAdapter());
    }

    private void checkValidation() {
        String messageContent = messageEt.getText().toString();

        if(messageContent.isEmpty()){
            Toast.makeText(this, getString(R.string.emptyMessagesError),Toast.LENGTH_LONG).show();
        }
        else{
            mMessageToSend = new Message(Authentication.getInstance().getLoggedInUser(), chatPartner, messageContent);
            sendMessage();
            messageEt.onEditorAction(EditorInfo.IME_ACTION_DONE);
        }
    }

    private void sendMessage() {
        MessagesRepository.getInstance().SendMessage(mMessageToSend, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    messageEt.setText(null);
                }
                else{
                    Toast.makeText(ChatActivity.this, task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
