package com.example.finalproject.Features;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.Instance.Room;
import com.example.finalproject.R;
import com.example.finalproject.RoomsLogic.RoomsManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class AddRoomDialog{

    Context mContext;
    AlertDialog.Builder mBuilder;
    AlertDialog mDialog;
    View mView;
    Button mNaturalBtn, mNegativeBtn;
    ImageButton mExitBtn;
    EditText mRoomNumber, mRoomFloor;
    ProgressBar mProgressBar;

    public AddRoomDialog(Context iContext){
        mContext=iContext;
        mView = ((AppCompatActivity)iContext).getLayoutInflater().inflate(R.layout.addroom_dialog,null, false);

        setViews();

        mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setView(mView);
        mDialog = mBuilder.create();
    }

    private void setViews(){
        mNaturalBtn = mView.findViewById(R.id.addRoomNaturalBtn);
        mNegativeBtn = mView.findViewById(R.id.addRoomNegativeBtn);
        mRoomNumber = mView.findViewById(R.id.addRoomNumber);
        mRoomFloor = mView.findViewById(R.id.addRoomFloor);
        mProgressBar = mView.findViewById(R.id.addRoomProgressBar);
        mExitBtn = mView.findViewById(R.id.exitBtnAddRoom);

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
                mDialog.dismiss();
            }
        });
    }

    public void show()
    {
        mDialog.show();
    }

    private void addRoom(Room iRoomToAdd){
        mProgressBar.setVisibility(View.VISIBLE);

        RoomsManager.getInstance().getRoomsRepository().AddRoom(iRoomToAdd, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(!task.isSuccessful()){
                    Toast.makeText(mContext, task.getException().getMessage(), Toast.LENGTH_SHORT).show();}
            }
        });

        mProgressBar.setVisibility(View.INVISIBLE);
    }

    private void checkValidation(){
        Room roomToAdd = new Room("1", "12");

        String roomFloor = mRoomFloor.getText().toString(),
                roomNumber = mRoomNumber.getText().toString();

        if(roomNumber.isEmpty()){
            mRoomNumber.setError("room number empty");
            mRoomNumber.requestFocus();
        }
        else if(roomFloor.isEmpty()){
            mRoomFloor.setError("mRoomFloor empty");
            mRoomFloor.requestFocus();
        }
        else{roomToAdd = new Room(roomNumber, roomFloor);
            addRoom(roomToAdd);}
    }
}
