package com.example.finalproject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class RegistrationFragment extends Fragment{

    EditText userFirstNameEt, userLastNameEt, userEmailEt, userPasswordEt;
    CheckBox roleCheckBox;
    Button registerBtn;
    User mRegisterUser = null;

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

        View mView = inflater.inflate(R.layout.fragment_registration, container, false);

        userEmailEt = mView.findViewById(R.id.inputEmail);
        userPasswordEt = mView.findViewById(R.id.inputPassword);
        registerBtn = mView.findViewById(R.id.registerButton);
        userFirstNameEt=mView.findViewById(R.id.inputFirstName);
        userLastNameEt=mView.findViewById(R.id.inputLastName);
        roleCheckBox=mView.findViewById(R.id.roleCheckbox);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validationDetails();
                Authentication.SignUp(mRegisterUser, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(mView.getContext(), "great", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(mView.getContext(), "Shit", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        return mView;
    }

    private void validationDetails(){
        String userEmail = userEmailEt.getText().toString(),
                userPassword = userPasswordEt.getText().toString(),
                userFirstName = userFirstNameEt.getText().toString(),
                userLastName = userLastNameEt.getText().toString();

        if(userFirstName.isEmpty()){
            userFirstNameEt.setError("first name empty");
            userFirstNameEt.requestFocus();
        }
        else if(userLastName.isEmpty()){
            userEmailEt.setError("userLastNameEt empty");
            userEmailEt.requestFocus();
        }
        else if(userEmail.isEmpty()){
            userEmailEt.setError("email empty");
            userEmailEt.requestFocus();
        }
       /* if(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            userEmailEt.setError("invalid email address");
            userEmailEt.requestFocus();

            return false;
        } */
        else if(userPassword.isEmpty()){
            userPasswordEt.setError("userPassword empty");
            userPasswordEt.requestFocus();
        }
        else {mRegisterUser = new User(userEmail, userPassword);}
    }
}
