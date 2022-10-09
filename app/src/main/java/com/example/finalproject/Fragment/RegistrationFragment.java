package com.example.finalproject.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.finalproject.Instance.User;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationFragment extends Fragment{

    EditText userFirstNameEt, userLastNameEt, userEmailEt, userPasswordEt;
    CheckBox roleCheckBox;
    Button registerBtn;

    View context;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

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

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        context = view;

        userEmailEt = view.findViewById(R.id.inputEmail);
        userPasswordEt = view.findViewById(R.id.inputPassword);
        registerBtn = view.findViewById(R.id.registerButton);
        userFirstNameEt=view.findViewById(R.id.inputFirstName);
        userLastNameEt=view.findViewById(R.id.inputLastName);
        roleCheckBox=view.findViewById(R.id.roleCheckbox);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validationDetails()){
                    temp();
                }
            }
        });


        return view;
    }

    @Override
    public void onStart() { super.onStart(); }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
    
    private boolean validationDetails(){

        String userEmail = userEmailEt.getText().toString(),
                userPassword = userPasswordEt.getText().toString(),
                userFirstName = userFirstNameEt.getText().toString(),
                userLastName = userLastNameEt.getText().toString();

        if(userFirstName.isEmpty()){
            userFirstNameEt.setError("first name empty");
            userFirstNameEt.requestFocus();

            return false;
        }
        if(userLastName.isEmpty()){
            userEmailEt.setError("userLastNameEt empty");
            userEmailEt.requestFocus();

            return false;
        }
        if(userEmail.isEmpty()){
            userEmailEt.setError("email empty");
            userEmailEt.requestFocus();

            return false;
        }
       /* if(Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
            userEmailEt.setError("invalid email address");
            userEmailEt.requestFocus();

            return false;
        } */
        if(userPassword.isEmpty()){
            userPasswordEt.setError("userPassword empty");
            userPasswordEt.requestFocus();

            return false;
        }

        return true;
    }

    private void temp(){

        firebaseAuth.createUserWithEmailAndPassword(userEmailEt.getText().toString(), userPasswordEt.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    User user = new User(userEmailEt.getText().toString(),userPasswordEt.getText().toString());

                    FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(
                            new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(context.getContext(), "good", Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(context.getContext(), "cos emeck", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else{
                    Toast.makeText(context.getContext(), "cos emeck X33333", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
