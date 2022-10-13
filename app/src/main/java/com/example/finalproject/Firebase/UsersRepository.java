package com.example.finalproject.Firebase;

import androidx.annotation.NonNull;

import com.example.finalproject.Instance.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UsersRepository {
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;
    ArrayList<User> mUsersList = new ArrayList<>();

    public UsersRepository() {
        mDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("users");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mUsersList.clear();

                for(DataSnapshot dateSnapshot: snapshot.getChildren()){
                    User user = dateSnapshot.getValue(User.class);
                    mUsersList.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public ArrayList<User> getUsersList() { return mUsersList; }

    public void AddUser(User iUserToAdd, OnCompleteListener iOnCompleteListener){
        if(!mUsersList.contains(iUserToAdd)) {
            mDatabaseReference.child(iUserToAdd.getID()).setValue(iUserToAdd).addOnCompleteListener(iOnCompleteListener);
        }
    }
}
