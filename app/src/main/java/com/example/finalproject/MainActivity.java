package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavHostController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject.Fragment.CalenderFragment;
import com.example.finalproject.Fragment.ChatFragment;
import com.example.finalproject.Fragment.ConversationFragment;
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

                            Navigation.findNavController(MainActivity.this,R.id.fragmentContainer).navigate(R.id.loginFragment);
                            header.setVisibility(View.GONE);
                            break;
                        case R.id.mi_calender:
                            //Navigation.findNavController(MainActivity.this,R.id.fragmentContainer).navigate(R.id.);
                            break;
                        case R.id.mi_chat:
                            Navigation.findNavController(MainActivity.this,R.id.fragmentContainer).navigate(R.id.conversationFragment);
                            break;
                        case R.id.mi_room:
                            Navigation.findNavController(MainActivity.this,R.id.fragmentContainer).navigate(R.id.chatFragment);
                            break;
                        default:
                            break;
                    }

                    return true;
                }
            };
}