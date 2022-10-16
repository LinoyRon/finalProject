package com.example.finalproject.Meetings;

import com.example.finalproject.Fragments.CalenderFragment;
import com.example.finalproject.Instance.Meeting;

import java.util.List;

public class MeetingManager {

    private MeetingAdapter mMeetingAdapter;
    private List<Meeting> mMeeting;
    //MeetingsRepository.NotifyAdapterListener notifyAdapterListenerFromRepository;
    CalenderFragment.NotifyAdapterListener notifyAdapterListenerFromActivity;

    public MeetingManager(String iDate) {
        mMeeting = MeetingsRepository.getInstance().getMeetingListByDate(iDate);
        mMeetingAdapter = new MeetingAdapter(mMeeting);

      /*  notifyAdapterListenerFromRepository = iMeeting -> {
            //mMeeting.add(iMeeting);
            mMeeting = MeetingsRepository.getInstance().getMeetingListByDate(iDate);
            mMeetingAdapter.notifyDataSetChanged();
        };*/

        //MeetingsRepository.getInstance().setListener(notifyAdapterListenerFromRepository);

        notifyAdapterListenerFromActivity = new CalenderFragment.NotifyAdapterListener() {
            @Override
            public void getListByDate(String iDate) {
                mMeeting = MeetingsRepository.getInstance().getMeetingListByDate(iDate);
                mMeetingAdapter.notifyDataSetChanged();
            }
        };

        CalenderFragment.setNotifyAdapterListener(notifyAdapterListenerFromActivity);
    }

    public MeetingAdapter getMeetingAdapter() { return mMeetingAdapter; }
}
