package com.example.finalproject.Firebase;

import androidx.annotation.Nullable;

import com.example.finalproject.Instance.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class UsersRepository {

    ArrayList<User> mUsersList = new ArrayList<>();
    FirebaseFirestore db;
    CollectionReference collectionReference;
    User mLoggedInUser;

    public UsersRepository() {
        db = FirebaseFirestore.getInstance();
        collectionReference = db.collection("users");

        collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    System.err.println("Listen failed: " + error);
                    return;
                }
                if(snapshot!=null){
                    mUsersList.clear();
                    User user;

                    for(DocumentSnapshot documentSnapshot: snapshot.getDocuments()){
                        user = documentSnapshot.toObject(User.class);

                        if(Objects.equals(user.getID(), FirebaseAuth.getInstance().getCurrentUser().getUid())){
                            mLoggedInUser = user;
                        }
                        else{
                            mUsersList.add(user);
                        }
                    }
                }
            }
        });
    }

    public ArrayList<User> getUsersList() {
        return mUsersList;
    }

    public void AddUser(User iUserToAdd, OnCompleteListener iOnCompleteListener) {
        //mDatabaseReference.child(iUserToAdd.getID()).setValue(iUserToAdd).addOnCompleteListener(iOnCompleteListener);
        collectionReference.document(iUserToAdd.getID()).set(iUserToAdd).addOnCompleteListener(iOnCompleteListener);
    }

    public void UpdateProfileImage(User iUserToUpdate, String iImagePath, OnCompleteListener iOnCompleteListener) {
        iUserToUpdate.setPhotoPath(iImagePath);
        //mDatabaseReference.child(iUserToUpdate.getID()).child("photoPath").setValue(iImagePath);
        collectionReference.document(iUserToUpdate.getID()).update("photoPath", iImagePath).addOnCompleteListener(iOnCompleteListener);
    }

    public User getLogUser() {
        return mLoggedInUser;
    }
}
