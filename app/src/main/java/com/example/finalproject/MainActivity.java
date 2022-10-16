package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.Features.ForgotPasswordDialog;
import com.example.finalproject.Features.RegistrationDialog;
import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Instance.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    EditText userEmailEt, userPasswordEt;
    Button loginBtn;
    TextView forgotPassword, register;
    User mSignInUser;
    ProgressBar myProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    private void setViews() {
        userEmailEt = findViewById(R.id.inputEmail);
        userPasswordEt = findViewById(R.id.inputPassword);
        loginBtn = findViewById(R.id.btnLogin);
        forgotPassword = findViewById(R.id.forgotPassword);
        myProgressBar= findViewById(R.id.progressBar);
        register = findViewById(R.id.registerToSystem);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrationDialog registrationDialog = new RegistrationDialog(view.getContext());
                registrationDialog.show();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validationDetails();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgotPasswordDialog forgotPasswordDialog = new ForgotPasswordDialog(MainActivity.this);
                forgotPasswordDialog.show();
            }
        });
    }

    private void validationDetails(){
        myProgressBar.setVisibility(View.VISIBLE);
        String userEmail = userEmailEt.getText().toString(),
                userPassword = userPasswordEt.getText().toString();

        if(userEmail.isEmpty()){
            myProgressBar.setVisibility(View.GONE);
            userEmailEt.setError(getResources().getString(R.string.emailRequired));
            userEmailEt.requestFocus();
        }
        else if(userPassword.isEmpty()){
            myProgressBar.setVisibility(View.GONE);
            userPasswordEt.setError(getResources().getString(R.string.passwordReqired));
            userPasswordEt.requestFocus();
        }
        else{mSignInUser = new User(userEmail, userPassword);
            logIn();}
    }

    private void logIn() {
        Authentication.getInstance().LogIn(mSignInUser, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if(task.isSuccessful()){
                    Intent intent = new Intent(MainActivity.this, FragmentsActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(MainActivity.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
                myProgressBar.setVisibility(View.GONE);
            }
        });
    }
}