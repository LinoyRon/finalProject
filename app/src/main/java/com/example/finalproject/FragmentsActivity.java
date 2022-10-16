package com.example.finalproject;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.example.finalproject.Features.AddMeetingDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FragmentsActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    FloatingActionButton addMeetingBtn;
    TextView header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_main);

        header=findViewById(R.id.mainActTopHeaderText);
        addMeetingBtn=findViewById(R.id.fab);
        addMeetingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddMeetingDialog addMeetingDialog = new AddMeetingDialog(FragmentsActivity.this);
                addMeetingDialog.show();
            }
        });

        initializeBottomNav();
    }

    private void initializeBottomNav() {
        bottomNav = findViewById(R.id.bottomNavigationView);

        bottomNav.setBackground(null);
        bottomNav.getMenu().findItem(R.id.placeholder).setEnabled(false);

        bottomNav.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch(item.getItemId()){
                        case R.id.mi_profile:
                            header.setText(R.string.profile);
                            Navigation.findNavController(FragmentsActivity.this,R.id.fragmentContainer).navigate(R.id.profileFragment);
                            break;
                        case R.id.mi_calender:
                            header.setVisibility(View.GONE);
                            Navigation.findNavController(FragmentsActivity.this,R.id.fragmentContainer).navigate(R.id.calenderFragment);
                            break;
                        case R.id.mi_chat:
                            header.setText(R.string.chat);
                            Navigation.findNavController(FragmentsActivity.this,R.id.fragmentContainer).navigate(R.id.conversationFragment);
                            break;
                        case R.id.mi_room:
                            header.setText(R.string.room);
                            Navigation.findNavController(FragmentsActivity.this,R.id.fragmentContainer).navigate(R.id.roomsFragment);
                            break;
                        default:
                            break;
                    }

                    return true;
                }
            };
}