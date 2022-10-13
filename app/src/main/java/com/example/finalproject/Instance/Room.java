package com.example.finalproject.Instance;

import com.example.finalproject.Firebase.Authentication;

public class Room {
    String RoomNumber, RoomFloor;
    User Owner;
    Boolean isAvailable;

    public Room() {}

    public Room(String mRoomNumber, String mRoomFloor) {
        Owner = Authentication.getLoggedInUser();
        isAvailable = true;
        RoomNumber = mRoomNumber.trim();
        RoomFloor = mRoomFloor.trim();
    }

    public String getRoomNumber() {
        return RoomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        RoomNumber = roomNumber;
    }

    public String getRoomFloor() {
        return RoomFloor;
    }

    public void setRoomFloor(String roomFloor) {
        RoomFloor = roomFloor;
    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) {
        Owner = owner;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }
}
