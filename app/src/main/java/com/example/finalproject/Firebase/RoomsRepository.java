package com.example.finalproject.Firebase;

import androidx.annotation.NonNull;

import com.example.finalproject.Instance.Room;
import com.example.finalproject.RoomsLogic.RoomsManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomsRepository {
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;
    ArrayList<Room> mRoomList = new ArrayList<>();

    public RoomsRepository() {
        mDatabase=FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference("rooms");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dateSnapshot: snapshot.getChildren()){
                    Room room = dateSnapshot.getValue(Room.class);
                    mRoomList.add(room);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public ArrayList<Room> getRoomList() { return mRoomList; }

    public void AddRoom(Room iRoomToAdd, OnCompleteListener onCompleteListener){
        mDatabaseReference.child(iRoomToAdd.getRoomNumber()).setValue(iRoomToAdd).addOnCompleteListener(onCompleteListener);
        //FirebaseDatabase.getInstance().getReference("rooms").child(iRoomToAdd.getRoomNumber()).setValue(iRoomToAdd);
    }
}
