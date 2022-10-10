package com.example.finalproject.Repository;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.finalproject.Instance.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RepositoryManager {
    private static final RepositoryManager repositoryInstance = new RepositoryManager();
    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private final DatabaseReference databaseReference;
    private User userTemp;


    public DatabaseReference GetDatabaseReference() { return databaseReference;  }

    public static RepositoryManager GetInstance(){ return repositoryInstance; }

    //the last user that sing in or log in or log out
    public static FirebaseUser GetCurrentUser(){ return firebaseAuth.getCurrentUser(); }

    private RepositoryManager() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userTemp = snapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });
    }

    public Boolean AddUser(User iUser, Context context) {
        final Boolean[] isSuccessful = {false};

        firebaseAuth.createUserWithEmailAndPassword(iUser.getEmail(), iUser.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if(task.isSuccessful()) {
                             FirebaseDatabase.getInstance().getReference("users").child(firebaseAuth.getCurrentUser().getUid()).setValue(iUser).addOnCompleteListener(
                                     new OnCompleteListener<Void>() {
                                         @Override
                                         public void onComplete(@NonNull Task<Void> task) {
                                             if(task.isSuccessful()){
                                                 isSuccessful[0] = true;
                                                 Toast.makeText(context, "good", Toast.LENGTH_LONG).show();
                                             }
                                         }
                                     });}
                     }
        });
        return isSuccessful[0];
    }

    public Boolean SignIn(User iUser, Context context) {
        final Boolean[] isSuccessful = {false};

        firebaseAuth.signInWithEmailAndPassword(iUser.getEmail(),iUser.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    isSuccessful[0] = true;
                }
                else {
                    Toast.makeText(context,"error"+ task.getException().getMessage(),Toast.LENGTH_LONG);
                }
            }
        });

        return isSuccessful[0];
    }

    public void SignOut() {
        firebaseAuth.signOut();
    }
}
