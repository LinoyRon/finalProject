package com.example.finalproject.Fragments;

import android.content.Context;
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
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Features.AddRoomDialog;
import com.example.finalproject.Features.PopUpDialog;
import com.example.finalproject.Instance.Room;
import com.example.finalproject.R;
import com.example.finalproject.RoomsLogic.MyItemTouchHelper;
import com.example.finalproject.RoomsLogic.RoomsAdapter;
import com.example.finalproject.RoomsLogic.RoomsManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;

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

        ItemTouchHelper.Callback callback = new MyItemTouchHelper(RoomsManager.getInstance().getRoomAdapter());
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);

        RoomsManager.getInstance().getRoomAdapter().SetTouchHelper(itemTouchHelper);
        itemTouchHelper.attachToRecyclerView(myRoomRecyclerView);

        RoomsManager.getInstance().getRoomAdapter().setMyRoomListener(new RoomsAdapter.MyRoomListener() {
            @Override
            public void onRoomClicked(Room clickedRoom, TextView ownerName, ImageView roomStatusImage) {
                if(clickedRoom.getAvailable()){
                    PopUpDialog.PopUpDialogListener Listener = new PopUpDialog.PopUpDialogListener() {
                        @Override
                        public void onNaturalBtnClick() {//reserve the room
                            ownerName.setVisibility(View.VISIBLE);
                            clickedRoom.setAvailable(false);
                            roomStatusImage.setImageResource(R.drawable.ic_occupied);
                        }

                        @Override
                        public void onNegativeBtnClick() {

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

            @Override
            public void onRoomSwiped(Room iRoomToRemove) {
                PopUpDialog.PopUpDialogListener Listener = new PopUpDialog.PopUpDialogListener() {
                    @Override
                    public void onNaturalBtnClick() {
                        int iPosition = RoomsManager.getInstance().getRoomsList().indexOf(iRoomToRemove);

                        RoomsManager.getInstance().getRoomsRepository().RemoveRoom(iRoomToRemove, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if(task.isSuccessful()){
                                    undoDelete(iRoomToRemove, iPosition);
                                }else{
                                    Toast.makeText(myView.getContext(),task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    @Override
                    public void onNegativeBtnClick() {

                    }
                };
                PopUpDialog.newInstance(
                        getString(R.string.deleteDialogTitel),
                        getString(R.string.deleteDialogDescribation),
                        getString(R.string.yes),
                        getString(R.string.no),
                        Listener,
                        true
                ).show(getParentFragmentManager(), "POP_UP_DIALOG");
            }
        });

        myRoomRecyclerView.setAdapter(RoomsManager.getInstance().getRoomAdapter());
    }

    private void undoDelete(Room iRemovedRoom, int iRoomPosition) {
        Snackbar.make(myView,R.string.roomRemoved, Snackbar.LENGTH_LONG).setAction(R.string.undo, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RoomsManager.getInstance().getRoomsList().add(iRoomPosition, iRemovedRoom);
                RoomsManager.getInstance().getRoomAdapter().notifyItemInserted(iRoomPosition);
                RoomsManager.getInstance().getRoomsRepository().AddRoom(iRemovedRoom, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(myView.getContext(),task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).show();
    }
}
