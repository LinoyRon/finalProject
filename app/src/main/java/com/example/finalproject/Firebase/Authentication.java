package com.example.finalproject.Firebase;

import com.example.finalproject.Instance.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {

    private static FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();;
    private static FirebaseUser firebaseUser;
    private static User mLoggedInUser = new User(" ", " ");

    public static void LogIn(User iLogUser, OnCompleteListener iOnCompleteListener){
        firebaseAuth.signInWithEmailAndPassword(iLogUser.getEmail(),iLogUser.getPassword()).addOnCompleteListener(iOnCompleteListener);
    }

    public static void SignUp(User iSignUser, OnCompleteListener iOnCompleteListener) {
        firebaseAuth.createUserWithEmailAndPassword(iSignUser.getEmail(), iSignUser.getPassword()).addOnCompleteListener(iOnCompleteListener);
    }

    public static FirebaseUser getFirebaseUser() {
        return firebaseUser = firebaseAuth.getCurrentUser();
    }

    public static User getLoggedInUser() {
        return mLoggedInUser;
    }

    public static void setLoggedInUser(User mLoggedInUser) {
        Authentication.mLoggedInUser = mLoggedInUser;
    }
}
