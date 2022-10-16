package com.example.finalproject.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Instance.User;
import com.example.finalproject.MainActivity;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.File;

public class ProfileFragment extends Fragment {

    final int PICK_FROM_GALLERY = 2;
    final int CAMERA_REQUEST = 3;
    File file;

    User loginUser;
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

        fullNameTv.setText(loginUser.getFullName());
        emailTv.setText(loginUser.getEmail());

        if(loginUser.getPhotoPath()!=null){
            userImageView.setImageURI(Uri.parse(loginUser.getPhotoPath()));}

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

        userImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopUpMenu();
            }
        });
    }

    private void showPopUpMenu() {
        PopupMenu popupMenu = new PopupMenu(getContext(), userImageView);
        popupMenu.inflate(R.menu.add_photo_menu);

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mi_camera:
                        onCameraClick();
                        break;
                    case R.id.mi_gallery:
                        onGalleryClick();
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void onCameraClick() {
        file = new File(getActivity().getExternalFilesDir(null), "pic.jpg");

        Uri imageUri = FileProvider.getUriForFile(getContext(),
                "com.example.finalproject.provider",
                file);

        // Toast.makeText(AddSongActivity.this, imageUri.toString(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, CAMERA_REQUEST);
    }

    private void onGalleryClick() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");

        startActivityForResult(galleryIntent, PICK_FROM_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String photoPath;
        Uri imageUri = null;

        if (requestCode == PICK_FROM_GALLERY && resultCode == AppCompatActivity.RESULT_OK) {
            if (data != null) {
                imageUri = data.getData();
                userImageView.setImageURI(imageUri);
            }
        }
        if (requestCode == CAMERA_REQUEST && resultCode == AppCompatActivity.RESULT_OK) {
            photoPath = file.getAbsolutePath();
            imageUri = Uri.parse(photoPath);
        }
        else { return; }

        Glide.with(this.getContext()).load(imageUri).into(userImageView);
        Authentication.getInstance().getUsersRepository().UpdateProfileImage(loginUser, imageUri.getPath(), newOnCompleteListener());
    }

    private OnCompleteListener newOnCompleteListener(){
        return new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getContext(), getString(R.string.profileUpdate), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), task.getException().getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            }
        };
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        loginUser = Authentication.getInstance().getLoggedInUser();
        initializeViews();

        return view;
    }
}
