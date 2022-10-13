package com.example.finalproject.Instance;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import com.example.finalproject.Firebase.Authentication;

public class User implements Parcelable {
    private String Email, Password, PhotoPath, FirstName, LastName, ID;
    Boolean isAdmin;

    public User() {
    }

    public User(String email, String password, String photoPath, String firstName, String lastName, Boolean isAdmin) {
        Email = email.trim();
        Password = password.trim();
        PhotoPath = photoPath;
        FirstName = firstName;
        LastName = lastName;
        this.isAdmin = isAdmin;
    }

    public User(String email, String password) {
        FirstName="Testing";
        LastName="Testing";
        ID= Authentication.getInstance().getFirebaseUser().getUid();
        Email = email.trim();
        Password = password.trim();
        this.isAdmin = false;
        PhotoPath="https://i2.wp.com/www.siasat.com/wp-content/uploads/2018/03/Rosamund-Pike.jpeg?fit=600%2C421&ssl=1";
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhotoPath() {
        return PhotoPath;
    }

    public void setPhotoPath(String photoPath) {
        PhotoPath = photoPath;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserFullName(){
        return FirstName + " " + LastName;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected User(Parcel in) {
        ID = in.readString();
        Email = in.readString();
        Password = in.readString();
        FirstName = in.readString();
        LastName = in.readString();
        PhotoPath = in.readString();
        isAdmin =  in.readBoolean();
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() { return 0;  }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(ID);
        dest.writeString(Email);
        dest.writeString(Password);
        dest.writeString(FirstName);
        dest.writeString(LastName);
        dest.writeString(PhotoPath);
        dest.writeBoolean(isAdmin);
    }
}
