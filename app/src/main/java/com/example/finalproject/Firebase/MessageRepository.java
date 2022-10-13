package com.example.finalproject.Firebase;

import androidx.annotation.NonNull;

import com.example.finalproject.Instance.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageRepository {
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;
    ArrayList<Message> mMessagesList = new ArrayList<>();

    public MessageRepository() {
        mDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("messages");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mMessagesList.clear();

                for(DataSnapshot dateSnapshot: snapshot.getChildren()){
                    Message message = dateSnapshot.getValue(Message.class);
                    mMessagesList.add(message);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public ArrayList<Message> getRoomList() { return mMessagesList; }

    public void AddMessage(Message iMessageToAdd, OnCompleteListener onCompleteListener){
        mDatabaseReference.child(iMessageToAdd.getConversationId()).setValue(iMessageToAdd).addOnCompleteListener(onCompleteListener);
    }
}
