package com.example.finalproject.Meetings;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;

import com.example.finalproject.Instance.Meeting;
import com.example.finalproject.Instance.Room;
import com.example.finalproject.R;
import com.example.finalproject.Rooms.RoomsManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Calendar;

public class AddMeetingDialog {

    Context mContext;
    AlertDialog.Builder mBuilder;
    AlertDialog mDialog;
    View mView;
    Button mNaturalBtn, mNegativeBtn;
    ImageButton mExitBtn;
    Button mSelectTime, mSelectRoom, mSelectDateBt;
    EditText Title;
    PopupMenu roomOptionPopUp;
    String meetingTitle, meetingLocation, meetingDate, meetingTime;
    Calendar mCalendar;
    int mYear, mMonth, mDay, mHour, mMin;

    public AddMeetingDialog(Context iContext){
        mContext=iContext;
        mView = ((AppCompatActivity)iContext).getLayoutInflater().inflate(R.layout.addmeeting_dialog,null, false);

        setTodayDateAndTime();
        setViews();
        showPopUpRoomOption();
        setDatePicker();
        setTimePicker();

        mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setView(mView);
        mDialog = mBuilder.create();
    }

    private void setTodayDateAndTime(){
        mCalendar = Calendar.getInstance();
        mYear = mCalendar.get(Calendar.YEAR);
        mMonth = mCalendar.get(Calendar.MONTH);
        mDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        mHour = mCalendar.get(Calendar.HOUR);
        mMin = mCalendar.get(Calendar.MINUTE);
    }

    private void setViews(){
        mNaturalBtn = mView.findViewById(R.id.addMeetingNaturalBtn);
        mNegativeBtn = mView.findViewById(R.id.addMeetingNegativeBtn);
        mExitBtn = mView.findViewById(R.id.exitBtnAddMeeting);
        mSelectTime = mView.findViewById(R.id.eventTime);
        mSelectRoom = mView.findViewById(R.id.evenLocation);
        mSelectDateBt = mView.findViewById(R.id.eventDate);
        Title = mView.findViewById(R.id.eventTitle);

        mSelectDateBt.setText(mDay+"/"+mMonth+"/"+mYear);
        mSelectTime.setText(mHour+":"+mMin);

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

        mSelectRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomOptionPopUp.show();
            }
        });
    }

    private void checkValidation() {
        Meeting meetingToAdd=null ;

        meetingTitle = Title.getText().toString();
        meetingDate = mSelectDateBt.getText().toString();
        meetingTime = mSelectTime.getText().toString();

        if(meetingTitle.isEmpty()){
            Title.setError(mContext.getString(R.string.titleEmpy).toString());
            Title.requestFocus();
        }
        else{meetingToAdd = new Meeting(meetingTitle,meetingLocation, meetingDate, meetingTime);
            addMeeting(meetingToAdd);
            mDialog.dismiss();
        }
    }

    private void addMeeting(Meeting iMeetingToSchedule) {
        MeetingsRepository.getInstance().AddMeeting(iMeetingToSchedule, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(mContext, mContext.getString(R.string.meetingAddedSuccessfully), Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void show(){ mDialog.show(); }

    private void setTimePicker() {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                meetingTime = i + ":" + i1;
                mSelectTime.setText(meetingTime);
            }
        };

        mSelectTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(mContext, listener, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mMonth, true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

                dialog.show();
            }
        });
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

    private void showPopUpRoomOption(){
        roomOptionPopUp = new PopupMenu(mContext, mSelectRoom);
        roomOptionPopUp.inflate(R.menu.rooms_menu);

        ArrayList<Room> rooms = RoomsManager.getInstance().getRoomsList();
        for (Room room : rooms ) {

            roomOptionPopUp.getMenu().add(Menu.NONE, Menu.NONE, Menu.NONE,
                    mContext.getString(R.string.room).toString()+" "+room.getRoomNumber()+", "+
                    mContext.getString(R.string.floor).toString()+" "+room.getRoomFloor());
        }

        meetingLocation =  roomOptionPopUp.getMenu().getItem(0).toString();

        roomOptionPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                meetingLocation = item.getTitle().toString();
                mSelectRoom.setText(meetingLocation);
                return false;
            }
        });
    }
}
