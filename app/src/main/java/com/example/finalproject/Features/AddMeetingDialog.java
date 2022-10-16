package com.example.finalproject.Features;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.finalproject.Instance.Room;
import com.example.finalproject.R;
import com.example.finalproject.RoomsLogic.RoomsManager;

import java.util.ArrayList;

public class AddMeetingDialog {

    Context mContext;
    AlertDialog.Builder mBuilder;
    AlertDialog mDialog;
    View mView;
    Button mNaturalBtn, mNegativeBtn;
    ImageButton mExitBtn;
    Button mSelectDate, mSelectRoom;
    EditText Title;
    PopupMenu timeOptionPopUp, roomOptionPopUp;
    String meetingTitle, meetingLocation, meetingTime;

    public AddMeetingDialog(Context iContext){
        mContext=iContext;
        mView = ((AppCompatActivity)iContext).getLayoutInflater().inflate(R.layout.addmeeting_dialog,null, false);

        setViews();
        showPopUpRoomOption();

        mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setView(mView);
        mDialog = mBuilder.create();
        showPopUpTimeOption();
    }

    private void setViews(){
        mNaturalBtn = mView.findViewById(R.id.addMeetingNaturalBtn);
        mNegativeBtn = mView.findViewById(R.id.addMeetingNegativeBtn);
        mExitBtn = mView.findViewById(R.id.exitBtnAddMeeting);
        mSelectDate = mView.findViewById(R.id.eventTime);
        mSelectRoom = mView.findViewById(R.id.evenLocation);
        Title = mView.findViewById(R.id.eventTitle);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        };

        mExitBtn.setOnClickListener(onClickListener);
        mNegativeBtn.setOnClickListener(onClickListener);
        mNaturalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addMeeting();
            }
        });

        mSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeOptionPopUp.show();
            }
        });

        mSelectRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomOptionPopUp.show();
            }
        });
    }

    private void showPopUpTimeOption(){
        timeOptionPopUp = new PopupMenu(mContext, mSelectDate);
        //   popup.getMenu().add(groupId, itemId, order, title);

        timeOptionPopUp.inflate(R.menu.time_menu);

        timeOptionPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                meetingTime = item.getTitle().toString();
                return false;
            }
        });
    }

    private void showPopUpRoomOption(){
        roomOptionPopUp = new PopupMenu(mContext, mSelectRoom);
        roomOptionPopUp.inflate(R.menu.rooms_menu);

        ArrayList<Room> rooms = RoomsManager.getInstance().getRoomsList();
        for (Room room : rooms ) {

            roomOptionPopUp.getMenu().add(Menu.NONE, Menu.NONE, Menu.NONE, room.getRoomNumber());
        }

        roomOptionPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                meetingLocation = item.getTitle().toString();
                return false;
            }
        });
    }

    private void addMeeting() {

    }


    public void show()
    {
        mDialog.show();
    }



}
