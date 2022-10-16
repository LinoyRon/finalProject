package com.example.finalproject.Meetings;

import androidx.annotation.Nullable;

import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Instance.Meeting;
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
   ArrayList<Meeting> mMeetingList = new ArrayList<>();

   public static MeetingsRepository getInstance() {
      return instance;
   }

   //MeetingsRepository.NotifyAdapterListener listener;
   //public interface NotifyAdapterListener{ void addMeeting(Meeting iMeeting); }

   public MeetingsRepository() {
      db = FirebaseFirestore.getInstance();
      collectionReference = db.collection("meeting");

      collectionReference.addSnapshotListener(new EventListener<QuerySnapshot>() {
         @Override
         public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
            if (error != null) {
               System.err.println("Listen failed: " + error);
               return;
            }
            if(snapshot!=null){
               mMeetingList.clear();

               for(DocumentSnapshot documentSnapshot: snapshot.getDocuments()){
                  Meeting meeting = documentSnapshot.toObject(Meeting.class);
                  if(Objects.equals(meeting.getSetUpMeetingUser().getID(), Authentication.getInstance().getLoggedInUser().getID())){
                     mMeetingList.add(meeting);
                  }
               }

               //order list by time
            }
         }
      });
   }

   public void AddMeeting(Meeting iMeetingToSchedule, OnCompleteListener iOnCompleteListener){
      collectionReference.add(iMeetingToSchedule).addOnCompleteListener(iOnCompleteListener);
      //listener.addMeeting(iMeetingToSchedule);
   }

  /* public void setListener(NotifyAdapterListener listener) {
      this.listener = listener;
   }*/

   public ArrayList<Meeting> getMeetingListByDate(String iDate) {

      ArrayList<Meeting> meetingListByDate = new ArrayList<>();

      for (Meeting meeting: mMeetingList) {
         if(Objects.equals(meeting.getDate(), iDate)){
            meetingListByDate.add(meeting);
         }
      }

      return meetingListByDate;
   }
}
