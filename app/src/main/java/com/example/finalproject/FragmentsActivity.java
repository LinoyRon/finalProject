package com.example.finalproject;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentsActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    TextView header;
    View addMeetingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_main);

        addMeetingBtn=findViewById(R.id.fab);
        header=findViewById(R.id.mainActTopHeaderText);

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
                            header.setText(R.string.calender);
                            //Navigation.findNavController(MainActivity.this,R.id.fragmentContainer).navigate(R.id.);
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