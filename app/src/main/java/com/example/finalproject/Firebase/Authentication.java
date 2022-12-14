package com.example.finalproject.Firebase;

import com.example.finalproject.Instance.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {

    Authentication.UserListener mListener;

    public interface UserListener {

        void onNegativeBtnClick();
    }

    private static Authentication mInstance = new Authentication();
    private FirebaseAuth firebaseAuth;
    private UsersRepository mUsersRepository;
    private User mLoggedInUser;

    private Authentication() {
        firebaseAuth = FirebaseAuth.getInstance();
        mUsersRepository = new UsersRepository();
    }

    public static Authentication getInstance() {  return mInstance;  }

    public void LogIn(User iLogUser, OnCompleteListener iOnCompleteListener) {
        firebaseAuth.signInWithEmailAndPassword(iLogUser.getEmail(), iLogUser.getPassword()).addOnCompleteListener(iOnCompleteListener);
    }

    public void SignUp(User iSignUser, OnCompleteListener iOnCompleteListener) {
        firebaseAuth.createUserWithEmailAndPassword(iSignUser.getEmail(), iSignUser.getPassword()).addOnCompleteListener(iOnCompleteListener);
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseAuth.getCurrentUser();
    }

    public User getLoggedInUser() {
        return mUsersRepository.getLogUser();
    }

    public UsersRepository getUsersRepository() {
        return mUsersRepository;
    }

    public void ResetPassword(String iEmail, OnCompleteListener iOnCompleteListener){
        firebaseAuth.sendPasswordResetEmail(iEmail).addOnCompleteListener(iOnCompleteListener);
    }

    public void LogOut(OnCompleteListener iOnCompleteListener) { FirebaseAuth.getInstance().signOut(); }
}
