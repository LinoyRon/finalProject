package com.example.finalproject.Instance;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.finalproject.R;

public class User implements Parcelable {
    private String Email, Password, PhotoPath, FirstName, LastName, ID, FullName;
    Boolean isAdmin = false;

    public User() {
    }

    public User(String email, String password) {
        Email = email;
        Password = password;
    }

    public User(String email, String password, String firstName, String lastName, Boolean isAdmin) {
        Email = email.trim();
        Password = password.trim();
        FirstName = firstName.trim();
        LastName = lastName.trim();
        FullName = FirstName + " " + LastName;
        PhotoPath = "android.resource://com.example.finalproject/app/src/main/res/drawable"+ R.drawable.ic_profile;
        this.isAdmin = isAdmin;
    }

    protected User(Parcel in) {
        Email = in.readString();
        Password = in.readString();
        PhotoPath = in.readString();
        FirstName = in.readString();
        LastName = in.readString();
        ID = in.readString();
        byte tmpIsAdmin = in.readByte();
        isAdmin = tmpIsAdmin == 0 ? null : tmpIsAdmin == 1;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    public String getFullName(){
        return FirstName + " " + LastName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Email);
        parcel.writeString(Password);
        parcel.writeString(PhotoPath);
        parcel.writeString(FirstName);
        parcel.writeString(LastName);
        parcel.writeString(ID);
        parcel.writeByte((byte) (isAdmin == null ? 0 : isAdmin ? 1 : 2));
    }
}
