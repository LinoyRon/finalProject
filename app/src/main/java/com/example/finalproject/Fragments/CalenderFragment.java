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

import com.example.finalproject.Calender.MeetingManager;
import com.example.finalproject.R;

public class CalenderFragment extends Fragment {

    private View myView;
    private CalendarView mCalendarView;
    private RecyclerView myMeetingRecyclerView;

    private String selectedDate;

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

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {


            }
        });

        return myView;
    }

    void setMyView(){
        mCalendarView = myView.findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                selectedDate = i + "/" + i1 + "/" + i2;
            }
        });
    }

    private void setRecyclerView(){
        myMeetingRecyclerView = myView.findViewById(R.id.calender_recyclerview);
        myMeetingRecyclerView.setHasFixedSize(true);
        myMeetingRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        myMeetingRecyclerView.setAdapter(new MeetingManager().getMeetingAdapter());
    }
}
