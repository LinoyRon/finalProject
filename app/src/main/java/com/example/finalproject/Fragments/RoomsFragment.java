package com.example.finalproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.ConversationLogic.ConversationManager;
import com.example.finalproject.R;
import com.example.finalproject.RoomsLogic.RoomsManager;

public class RoomsFragment extends Fragment {

    RecyclerView myRoomRecyclerView;
    View myView;

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
        myView =  inflater.inflate(R.layout.fragment_rooms, container, false);
        setRecyclerView();
        return myView;
    }

    private void setRecyclerView(){
        myRoomRecyclerView = myView.findViewById(R.id.roomsRecyclerview);
        myRoomRecyclerView.setHasFixedSize(true);
        myRoomRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));
        myRoomRecyclerView.setAdapter(new RoomsManager().getRoomAdapter());
    }
}
