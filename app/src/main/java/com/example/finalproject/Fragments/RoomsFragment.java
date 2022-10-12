package com.example.finalproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.ConversationLogic.ConversationManager;
import com.example.finalproject.Feature.AddRoomDialog;
import com.example.finalproject.Feature.PopUpDialog;
import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Firebase.RoomsRepository;
import com.example.finalproject.Instance.Room;
import com.example.finalproject.R;
import com.example.finalproject.RoomsLogic.RoomsAdapter;
import com.example.finalproject.RoomsLogic.RoomsManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class RoomsFragment extends Fragment {

    RecyclerView myRoomRecyclerView;
    View myView;
    Button temp;

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

        temp = myView.findViewById(R.id.temp);
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddRoomDialog addRoomDialog = new AddRoomDialog(myView.getContext());
                addRoomDialog.show();
            }
        });

        setRecyclerView();
        return myView;
    }

    private void setRecyclerView(){
        myRoomRecyclerView = myView.findViewById(R.id.roomsRecyclerview);
        myRoomRecyclerView.setHasFixedSize(true);
        myRoomRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));

        RoomsManager.getInstance().getRoomAdapter().setMyRoomListener(new RoomsAdapter.MyRoomListener() {
            @Override
            public void onRoomClicked(Room clickedRoom, TextView ownerName, ImageView roomStatusImage) {
                if(clickedRoom.isAvailable()){
                    PopUpDialog.PopUpDialogListener Listener = new PopUpDialog.PopUpDialogListener() {
                        @Override
                        public void onNaturalBtnClick() {//reserve the room
                            ownerName.setVisibility(View.VISIBLE);
                            clickedRoom.setAvailable(false);
                            roomStatusImage.setImageResource(R.drawable.ic_occupied);
                        }

                        @Override
                        public void onNegativeBtnClick() {
                            //do nothing
                        }
                    };
                    PopUpDialog.newInstance(
                            getString(R.string.reserveRoomTitel),
                            getString(R.string.reserveRoomDescribtion),
                            getString(R.string.yes),
                            getString(R.string.no),
                            Listener,
                            true
                    ).show(getParentFragmentManager(), "POP_UP_DIALOG");

                }else{
                    PopUpDialog.PopUpDialogListener Listener = new PopUpDialog.PopUpDialogListener() {
                        @Override
                        public void onNaturalBtnClick() {//reserve the room
                            ownerName.setVisibility(View.GONE);
                            clickedRoom.setAvailable(true);
                            roomStatusImage.setImageResource(R.drawable.ic_check);
                        }

                        @Override
                        public void onNegativeBtnClick() {
                            //do nothing
                        }
                    };
                    PopUpDialog.newInstance(
                            getString(R.string.freeRoomTitel),
                            getString(R.string.freeRoomDescribtion),
                            getString(R.string.yes),
                            getString(R.string.no),
                            Listener,
                            true
                    ).show(getParentFragmentManager(), "POP_UP_DIALOG");
                }
            }
        });

        myRoomRecyclerView.setAdapter(RoomsManager.getInstance().getRoomAdapter());
    }
}
