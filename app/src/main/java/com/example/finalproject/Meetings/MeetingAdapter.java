package com.example.finalproject.Meetings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Instance.Meeting;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.MeetingViewHolder> {

    private List<Meeting> mMeetingList = new ArrayList<>();
    Context m_Context;

    public MeetingAdapter(List<Meeting> mMeetingList) {
        this.mMeetingList = mMeetingList;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meeting_cell,parent,false);
        MeetingViewHolder ViewHolder = new MeetingViewHolder(view);

        m_Context = parent.getContext();

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingViewHolder holder, int position) {
        Meeting meeting = mMeetingList.get(position);
        
        holder.mTitle.setText(meeting.getTitle());
        holder.mLocation.setText(meeting.getLocation());
        holder.mTime.setText(meeting.getTime() + ", " + meeting.getDate());
    }

    @Override
    public int getItemCount() { return mMeetingList.size();  }

    protected class MeetingViewHolder extends RecyclerView.ViewHolder{

        TextView mTitle, mLocation, mTime;
       
        public MeetingViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle=itemView.findViewById(R.id.title_input);
            mLocation=itemView.findViewById(R.id.location_input);
            mTime=itemView.findViewById(R.id.time_input);
        }
    }
}
