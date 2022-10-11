package com.example.finalproject.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.finalproject.ChatLogic.MessageManager;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // clear old items / users from list to add new data/ users
                // myItemsList.clear();

                // getting all children from users root
                for (DataSnapshot users : snapshot.child("users").getChildren()) {

                    // to prevent app crash check if the user has all the details in Firebase Database
                    if (users.hasChild("fullname") && users.hasChild("mobile") && users.hasChild("email")) {

                        // getting users details from Firebase Database and store into the List one by one
                        final String getFullname = users.child("fullname").getValue(String.class);
                        final String getMobile = users.child("mobile").getValue(String.class);
                        final String getEmail = users.child("email").getValue(String.class);

                        // creating user item with user details
                        // MyItems myItems = new MyItems(getFullname, getMobile, getEmail);

                        // adding this user item to List
                        // myItemsList.add(myItems);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
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

    private void sendMessage(Message iMessage){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Chats").push().setValue(iMessage);
    }

    private void setRecyclerView(){
        myChatHistoryRecyclerView = findViewById(R.id.chattingHistoryRecycleView);
        myChatHistoryRecyclerView.setHasFixedSize(true);
        myChatHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myChatHistoryRecyclerView.setAdapter(new MessageManager().getMassageAdapter());
    }
}
