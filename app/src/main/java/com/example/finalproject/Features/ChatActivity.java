package com.example.finalproject.Features;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.ChatLogic.MessageManager;
import com.example.finalproject.Firebase.MessagesRepository;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {

    ImageView chatProfileImage;
    ImageButton sendMessageBtn;
    TextView chatUserName;
    EditText messageEt;
    User chatPartner, currentUser;
    String conversationId;
    RecyclerView myChatHistoryRecyclerView;
    Message mMessageToSend;

    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();
        currentUser = intent.getParcelableExtra("PARTNER_CHAT");
        chatPartner = intent.getParcelableExtra("USER");

        setViews();
        setRecyclerView();
    }

    private void setViews() {
        chatProfileImage = findViewById(R.id.chatPartnerProfile);
        sendMessageBtn = findViewById(R.id.chatSendBtn);
        chatUserName = findViewById(R.id.chatPartnerName);
        messageEt = findViewById(R.id.chatCommentBar);

//        chatUserName.setText(chatPartner.getUserFullName());
        /*Glide.with(this)
                .load(chatPartner.getPhotoPath())
                .circleCrop()
                .into(chatProfileImage);*/

        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkValidation();
            }
        });
    }

    private void setRecyclerView(){
        myChatHistoryRecyclerView = findViewById(R.id.chatHistoryRecycler);
        myChatHistoryRecyclerView.setHasFixedSize(true);
        myChatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myChatHistoryRecyclerView.setAdapter(new MessageManager().getMassageAdapter());
    }

    private void checkValidation() {
        String messageContent = messageEt.getText().toString();

        if(messageContent.isEmpty()){
            Toast.makeText(this, getString(R.string.emptyMessagesError),Toast.LENGTH_LONG).show();
        }
        else{
            mMessageToSend = new Message(currentUser, chatPartner, messageContent);
            sendMessage();
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
