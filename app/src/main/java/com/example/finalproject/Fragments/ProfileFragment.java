package com.example.finalproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ProfileFragment extends Fragment {

    private View view;
    private TextView fullNameTv, emailTv, logOutTv;
    ImageView userImageView;
    ProgressBar progressBar;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initializeViews(){
        fullNameTv = view.findViewById(R.id.outputFullName);
        emailTv = view.findViewById(R.id.outputEmail);
        userImageView = view.findViewById(R.id.userImageView);
        logOutTv = view.findViewById(R.id.logoutBtn);
        progressBar = view.findViewById(R.id.progressBarLogout);

        //fullNameTv.setText(Authentication.getInstance().getLoggedInUser().getUserFullName());
        //emailTv.setText(Authentication.getInstance().getLoggedInUser().getEmail());
        logOutTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                Authentication.getInstance().LogOut(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(view.getContext(), MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(view.getContext(),"GOOD",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(view.getContext(), task.getException().getMessage().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initializeViews();

        return view;
    }
}
