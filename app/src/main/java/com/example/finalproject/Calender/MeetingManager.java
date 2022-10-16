package com.example.finalproject.Calender;

import com.example.finalproject.Firebase.MeetingsRepository;
import com.example.finalproject.Instance.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingManager {

    private MeetingAdapter mMeetingAdapter;
    private List<Meeting> mMeeting;
    MeetingsRepository.NotifyAdapterListener listener;

    public MeetingManager() {
        init();
        mMeetingAdapter = new MeetingAdapter(mMeeting);
    }

    public MeetingAdapter getMeetingAdapter() { return mMeetingAdapter; }

    void init(){
        mMeeting = new ArrayList<>();

        mMeeting.add(new Meeting("title","location","TIME", "date"));
        mMeeting.add(new Meeting("title","location","TIME", "date"));
        mMeeting.add(new Meeting("title","location","TIME", "date"));
        mMeeting.add(new Meeting("title","location","TIME", "date"));
        mMeeting.add(new Meeting("title","location","TIME", "date"));
        mMeeting.add(new Meeting("title","location","TIME", "date"));
    }
}
