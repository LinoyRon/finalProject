package com.example.finalproject.Firebase;

import androidx.annotation.Nullable;

import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class MeetingsRepository {

   static MeetingsRepository instance = new MeetingsRepository();
   FirebaseFirestore db;
   CollectionReference collectionReference;
   ArrayList<Message> mMessageHistoryList = new ArrayList<>();
   NotifyAdapterListener listener;
   User mLogUser, iChatPartner;

   public interface NotifyAdapterListener{ void addMessage(Message iAddMessage); }

   public static MeetingsRepository getInstance() {
      return instance;
   }

   public void setListener(NotifyAdapterListener listener) {
      this.listener = listener;
   }

   public MeetingsRepository() {
      db = FirebaseFirestore.getInstance();
      collectionReference = db.collection("messages");
      mLogUser = Authentication.getInstance().getLoggedInUser();

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

                  if(Objects.equals(message.getSender().getID(), mLogUser.getID()) || Objects.equals(message.getReceiver().getID(), mLogUser.getID())){
                     mMessageHistoryList.add(message);
                  }
               }
            }
         }
      });
   }

   public void SendMessage(Message iSendingMessage, OnCompleteListener iOnCompleteListener){
      collectionReference.add(iSendingMessage).addOnCompleteListener(iOnCompleteListener);
      listener.addMessage(iSendingMessage);
   }

   public ArrayList<Message> getChatHistory(User iChatPartner){
      ArrayList<Message> chatHistory = new ArrayList<>();

      for (Message message: mMessageHistoryList) {
         if(Objects.equals(message.getSender().getID(), iChatPartner.getID()) || Objects.equals(message.getReceiver().getID(), iChatPartner.getID())){
            chatHistory.add(message);
         }
      }

      return chatHistory;
   }
}
