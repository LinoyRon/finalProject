package com.example.finalproject.Features;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.finalproject.Instance.Meeting;
import com.example.finalproject.Instance.Room;
import com.example.finalproject.R;
import com.example.finalproject.RoomsLogic.RoomsManager;

import java.util.ArrayList;
import java.util.Calendar;

public class AddMeetingDialog {

    Context mContext;
    AlertDialog.Builder mBuilder;
    AlertDialog mDialog;
    View mView;
    Button mNaturalBtn, mNegativeBtn;
    ImageButton mExitBtn;
    Button mSelectDate, mSelectRoom, mSelectDateBt;
    EditText Title;
    PopupMenu timeOptionPopUp, roomOptionPopUp;
    String meetingTitle, meetingLocation, meetingDate, meetingTime;

    Calendar mCalendar;
    int mYear, mMonth, mDay;

    public AddMeetingDialog(Context iContext){
        mContext=iContext;
        mView = ((AppCompatActivity)iContext).getLayoutInflater().inflate(R.layout.addmeeting_dialog,null, false);

        setTodayDate();
        setViews();
        showPopUpRoomOption();
        setDatePicker();

        mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setView(mView);
        mDialog = mBuilder.create();
        showPopUpTimeOption();
    }

    private void setTodayDate(){
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
    }

    private void setViews(){
        mNaturalBtn = mView.findViewById(R.id.addMeetingNaturalBtn);
        mNegativeBtn = mView.findViewById(R.id.addMeetingNegativeBtn);
        mExitBtn = mView.findViewById(R.id.exitBtnAddMeeting);
        mSelectDate = mView.findViewById(R.id.eventTime);
        mSelectRoom = mView.findViewById(R.id.evenLocation);
        mSelectDateBt = mView.findViewById(R.id.eventDate);
        Title = mView.findViewById(R.id.eventTitle);

        mSelectDateBt.setText(mDay+"/"+mMonth+"/"+mYear);

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
                checkValidation();
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

    private void checkValidation() {
        Meeting meetingToAdd=null ;

        if(meetingTitle.isEmpty()){
            Title.setError(mContext.getString(R.string.titleEmpy).toString());
            Title.requestFocus();
        }
        else if(meetingLocation.isEmpty()){
            mSelectRoom.setError(mContext.getString(R.string.roommeetingempty).toString());
            mSelectRoom.requestFocus();
        }
        else if(meetingTime.isEmpty()){
            mSelectDate.setError(mContext.getString(R.string.timeEmpty).toString());
            mSelectDate.requestFocus();
        }
        else{meetingToAdd = new Meeting(meetingTitle,meetingLocation, meetingTime);
           addMeeting();
            mDialog.dismiss();
        }
    }

    private void addMeeting() {

    }


    public void show()
    {
        mDialog.show();
    }

    private void setDatePicker() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                meetingDate = day + "/" + month + "/" + year;

                mSelectDateBt.setText(meetingDate);
            }
        };

        mSelectDateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(mContext, android.R.style.Theme_Holo_Light_Dialog_MinWidth, listener, mYear, mMonth, mDay);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                mCalendar.set(Calendar.YEAR, mYear);
                dialog.getDatePicker().setMinDate(mCalendar.getTimeInMillis());
                dialog.show();
            }
        });
    }

    private void showPopUpTimeOption(){
        timeOptionPopUp = new PopupMenu(mContext, mSelectDate);
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
}
