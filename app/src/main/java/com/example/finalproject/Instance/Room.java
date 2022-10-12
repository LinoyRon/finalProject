package com.example.finalproject.Instance;

import com.example.finalproject.Firebase.Authentication;

public class Room {
    String mRoomNumber, mRoomFloor;
    User mOwner;
    Boolean isAvailable;

    public Room() {}

    public Room(String mRoomNumber, String mRoomFloor) {
        this.mRoomNumber = mRoomNumber;
        this.mRoomFloor = mRoomFloor;
        this.mOwner = Authentication.getLoggedInUser();
        this.isAvailable = true;
    }

    public String getRoomNumber() {
        return mRoomNumber;
    }

    public String getRoomFloor() {
        return mRoomFloor;
    }

    public User getOwner() {
        return mOwner;
    }

    public void setAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }
}
