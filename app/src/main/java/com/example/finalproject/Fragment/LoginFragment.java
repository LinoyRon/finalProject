package com.example.finalproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.Instance.User;
import com.example.finalproject.R;
import com.example.finalproject.Repository.RepositoryManager;

public class LoginFragment extends Fragment{

    EditText userEmailEt, userPasswordEt;
    Button loginBtn;
    TextView forgotPassword;
    User signInUser;
    ProgressBar myProgressBar;

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

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        userEmailEt = view.findViewById(R.id.inputEmail);
        userPasswordEt = view.findViewById(R.id.inputPassword);
        loginBtn = view.findViewById(R.id.btnLogin);
        forgotPassword = view.findViewById(R.id.forgotPassword);
        myProgressBar=view.findViewById(R.id.progressBar);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myProgressBar.setVisibility(View.VISIBLE);
                validationDetails();

                if(signInUser!=null) {
                    if (RepositoryManager.GetInstance().SignIn(signInUser,view.getContext())) {

                        Intent intent = new Intent(view.getContext(), ProfileFragment.class);
                        startActivity(intent);
                        Toast.makeText(view.getContext(), "great", Toast.LENGTH_LONG).show();
                    }
                }

                myProgressBar.setVisibility(View.GONE);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    private void validationDetails(){

        String userEmail = userEmailEt.getText().toString(),
                userPassword = userPasswordEt.getText().toString();

        if(userEmail.isEmpty()){
            userEmailEt.setError("email empty");
            userEmailEt.requestFocus();
        }
        /*if(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            userEmailEt.setError("invalid email address");
            userEmailEt.requestFocus();

            return false;
        }*/
        if(userPassword.isEmpty()){
            userPasswordEt.setError("userPassword empty");
            userPasswordEt.requestFocus();
        }

        signInUser = new User(userEmail, userPassword);
    }
}
