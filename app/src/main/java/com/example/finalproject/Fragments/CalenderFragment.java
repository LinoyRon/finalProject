package com.example.finalproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Meetings.MeetingManager;
import com.example.finalproject.R;

import java.util.Calendar;

public class CalenderFragment extends Fragment {

    private static final String TAG = "CalenderFragment";
    private View myView;
    private CalendarView mCalendarView;
    private RecyclerView myMeetingRecyclerView;

    private String selectedDate;

    private static CalenderFragment.NotifyAdapterListener notifyAdapterListener;
    public interface NotifyAdapterListener{ void getListByDate(String iDate); }

    public static void setNotifyAdapterListener(NotifyAdapterListener iNotifyAdapterListener) {
        notifyAdapterListener = iNotifyAdapterListener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_calender, container, false);

        setMyView();
        setRecyclerView();

        return myView;
    }

    void setMyView(){
        Calendar mCalendar = Calendar.getInstance();
        int Year = mCalendar.get(Calendar.YEAR);
        int Month = mCalendar.get(Calendar.MONTH);
        int Day = mCalendar.get(Calendar.DAY_OF_MONTH);

        selectedDate = Day+"/"+Month+"/"+Year;
        mCalendarView = (CalendarView)myView.findViewById(R.id.CalenderView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                selectedDate = i2 + "/" + i1 + "/" + i;
                notifyAdapterListener.getListByDate(selectedDate);
            }
        });
    }

    private void setRecyclerView(){
        myMeetingRecyclerView = myView.findViewById(R.id.calender_recyclerview);
        myMeetingRecyclerView.setHasFixedSize(true);
        myMeetingRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        myMeetingRecyclerView.setAdapter(new MeetingManager(selectedDate).getMeetingAdapter());
    }
}
