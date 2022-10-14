package com.example.finalproject.Firebase;

import androidx.annotation.Nullable;

import com.example.finalproject.Instance.Message;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MessagesRepository {
   static MessagesRepository instance = new MessagesRepository();
   FirebaseFirestore db;
   CollectionReference collectionReference;
   ArrayList<Message> mMessageHistoryList = new ArrayList<>();

   public static MessagesRepository getInstance() {
      return instance;
   }

   public MessagesRepository() {
      db = FirebaseFirestore.getInstance();
      collectionReference = db.collection("messages");

      collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
         @Override
         public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
            if (error != null) {
               System.err.println("Listen failed: " + error);
               return;
            }
            if(snapshot!=null){
               mMessageHistoryList.clear();

               for(DocumentSnapshot documentSnapshot: snapshot.getDocuments()){
                  Message message = documentSnapshot.toObject(Message.class);
                  mMessageHistoryList.add(message);
               }
            }
         }
      });
   }

   public ArrayList<Message> getMessageHistoryList() {
      return mMessageHistoryList;
   }

   public void SendMessage(Message iSendingMessage, OnCompleteListener iOnCompleteListener){
      collectionReference.add(iSendingMessage).addOnCompleteListener(iOnCompleteListener);
   }
}
