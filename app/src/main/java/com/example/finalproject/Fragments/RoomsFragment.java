package com.example.finalproject.Fragments;

import android.content.Context;
import android.content.Intent;
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
import com.example.finalproject.Feature.PopUpDialog;
import com.example.finalproject.R;
import com.example.finalproject.RoomsLogic.RoomsAdapter;
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
        RoomsManager myRoomsManager = new RoomsManager();

        myRoomRecyclerView = myView.findViewById(R.id.roomsRecyclerview);
        myRoomRecyclerView.setHasFixedSize(true);
        myRoomRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));

        myRoomsManager.getRoomAdapter().setMyRoomListener(new RoomsAdapter.MyRoomListener() {
            @Override
            public void onRoomClicked(int adapterPosition) {
                PopUpDialog.PopUpDialogListener listener = new PopUpDialog.PopUpDialogListener() {
                    @Override
                    public void onNaturalBtnClick() {

                    }

                    @Override
                    public void onNegativeBtnClick() {
                        //do nothing
                    }
                };
                PopUpDialog.newInstance(
                        "title",
                        "תיאור",
                        "כפתור חיובי",
                        "כפתור שלילי",
                        listener,
                        true
                ).show(getParentFragmentManager(), "POP_UP_DIALOG");
            }
        });

        myRoomRecyclerView.setAdapter(myRoomsManager.getRoomAdapter());
    }
}
