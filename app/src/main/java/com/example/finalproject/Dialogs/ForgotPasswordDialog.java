package com.example.finalproject.Dialogs;

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

import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ForgotPasswordDialog {

    Context mContext;
    AlertDialog.Builder mBuilder;
    AlertDialog mDialog;
    View mView;
    Button mNaturalBtn, mNegativeBtn;
    ImageButton mExitBtn;
    EditText mEmailEt;
    ProgressBar mProgressBar;
    
    public ForgotPasswordDialog(Context iContext){
        mContext=iContext;
        mView = ((AppCompatActivity)iContext).getLayoutInflater().inflate(R.layout.forgotpassword_dialog,null, false);

        setViews();

        mBuilder = new AlertDialog.Builder(mContext);
        mBuilder.setView(mView);
        mDialog = mBuilder.create();
    }

    private void setViews(){
        mNaturalBtn = mView.findViewById(R.id.resetRoomNaturalBtn);
        mNegativeBtn = mView.findViewById(R.id.resetRoomNegativeBtn);
        mEmailEt = mView.findViewById(R.id.mailToReset);
        mExitBtn = mView.findViewById(R.id.exitResetDialog);
        mProgressBar = mView.findViewById(R.id.resetPasswordProgressBar);

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
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String userEmail = mEmailEt.getText().toString().trim();

        if(userEmail.isEmpty()) {
            mEmailEt.setError(mContext.getText(R.string.emailRequired));
            mEmailEt.requestFocus();
            return;
        }

        mProgressBar.setVisibility(View.VISIBLE);

        Authentication.getInstance().ResetPassword(userEmail, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(mView.getContext(),mContext.getText(R.string.resetSent),Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(mView.getContext(),task.getException().getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            }
        });

        mProgressBar.setVisibility(View.GONE);
        mDialog.dismiss();
    }

    public void show()
    {
        mDialog.show();
    }



}
