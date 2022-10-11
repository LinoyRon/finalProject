package com.example.finalproject.Instance;

public class Room {
    String mRoomNumber, mRoomFloor;
    User mOwner;
    Boolean isAvailable;

    public Room(String mRoomNumber, String mRoomFloor) {
        this.mRoomNumber = mRoomNumber;
        this.mRoomFloor = mRoomFloor;
        this.mOwner = null;
        this.isAvailable = false;
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

    public void setOwner(User iRoomOwner) {
        this.isAvailable = true;
        mOwner=iRoomOwner;
    }

    public Boolean isAvailable() {
        return isAvailable;
    }
}
