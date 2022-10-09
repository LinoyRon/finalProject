package com.example.finalproject.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.R;
import com.example.finalproject.Repository.RepositoryManager;

public class ProfileFragment extends Fragment {

    private View view;
    private TextView fullNameTv, emailTv;
    ImageView userImageView;

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

        if(RepositoryManager.GetCurrentUser()!=null){
            fullNameTv.setText(RepositoryManager.GetCurrentUser().getDisplayName());
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        initializeViews();

        return view;
    }
}
