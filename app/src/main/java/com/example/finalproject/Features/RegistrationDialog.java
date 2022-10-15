package com.example.finalproject.Features;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationDialog extends AppCompatActivity {

    Context mContext;
    AlertDialog.Builder mBuilder;
    AlertDialog mDialog;
    View mView;
    Button mNaturalBtn, mNegativeBtn;
    ImageButton mExitBtn;
    EditText userFirstNameEt, userLastNameEt, userEmailEt, userPasswordEt;
    CheckBox roleCheckBox;
    ProgressBar mProgressBar;
    User mRegisterUser;

    public RegistrationDialog(Context iContext){
        mContext=iContext;
        mView = ((AppCompatActivity)iContext).getLayoutInflater().inflate(R.layout.registration_dialog,null, false);

        setViews();

        mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setView(mView);
        mDialog = mBuilder.create();
    }

    private void setViews(){
        mNaturalBtn = mView.findViewById(R.id.addRoomNaturalBtn);
        mNegativeBtn = mView.findViewById(R.id.addRoomNegativeBtn);
        userEmailEt = mView.findViewById(R.id.inputEmail);
        userPasswordEt = mView.findViewById(R.id.inputPassword);
        userFirstNameEt=mView.findViewById(R.id.inputFirstName);
        userLastNameEt=mView.findViewById(R.id.inputLastName);
        roleCheckBox=mView.findViewById(R.id.roleCheckbox);
        mProgressBar = mView.findViewById(R.id.addRoomProgressBar);
        mExitBtn = mView.findViewById(R.id.exitBtnAddRoom);

        mExitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        mNaturalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationDetails();
            }
        });

/*        userProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpMenu();
            }
        });*/
    }

    public void show()
    {
        mDialog.show();
    }

    private void validationDetails(){
        String userEmail = userEmailEt.getText().toString(),
                userPassword = userPasswordEt.getText().toString(),
                userFirstName = userFirstNameEt.getText().toString(),
                userLastName = userLastNameEt.getText().toString();

        if(userFirstName.isEmpty()){
            userFirstNameEt.setError(mContext.getString(R.string.nameFirstReqierd));
            userFirstNameEt.requestFocus();
        }
        else if(userLastName.isEmpty()){
            userFirstNameEt.setError(mContext.getString(R.string.nameLastReqierd));
            userEmailEt.requestFocus();
        }
        else if(userEmail.isEmpty()){
            userEmailEt.setError(mContext.getString(R.string.emailRequired));
            userEmailEt.requestFocus();
        }
        else if(userPassword.isEmpty()){
            userPasswordEt.setError(mContext.getString(R.string.passwordReqired));
            userPasswordEt.requestFocus();
        }
        else if(userPassword.length() < 6){
            userPasswordEt.setError(mContext.getString(R.string.passwordLenght));
            userPasswordEt.requestFocus();
        }
        else {mRegisterUser = new User(userEmail, userPassword,userFirstName,userLastName,roleCheckBox.isChecked());
            registration();
            mDialog.dismiss();}
    }

    private void registration(){
        Authentication.getInstance().SignUp(mRegisterUser, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){

                    mRegisterUser.setID(FirebaseAuth.getInstance().getUid());

                    Authentication.getInstance().getUsersRepository().AddUser(mRegisterUser, new OnCompleteListener(){
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(mView.getContext(), task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(mView.getContext(), mContext.getString(R.string.userAdded), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }else{
                    Toast.makeText(mView.getContext(), task.getException().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
