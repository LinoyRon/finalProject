package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject.Fragment.CalenderFragment;
import com.example.finalproject.Fragment.ChatFragment;
import com.example.finalproject.Fragment.LoginFragment;
import com.example.finalproject.Fragment.ProfileFragment;
import com.example.finalproject.Fragment.RegistrationFragment;
import com.example.finalproject.Fragment.RoomsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    TextView header;
    View addMeetingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addMeetingBtn=findViewById(R.id.fab);
        header=findViewById(R.id.mainActTopHeaderText);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new ProfileFragment()).commit();

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
                    Fragment selectedFragment = null;

                    switch(item.getItemId()){
                        case R.id.mi_profile:
                            selectedFragment=new LoginFragment();
                            header.setVisibility(View.GONE);
                            break;
                        case R.id.mi_calender:
                            selectedFragment=new RegistrationFragment();//CalenderFragment();
                            break;
                        case R.id.mi_chat:
                            selectedFragment=new ChatFragment();
                            break;
                        case R.id.mi_room:
                            selectedFragment=new RoomsFragment();
                            break;
                        default:
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();
                    return true;
                }
            };
}