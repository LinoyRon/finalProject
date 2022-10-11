package com.example.finalproject.RoomsLogic;

import com.example.finalproject.ChatLogic.MassageAdapter;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.Room;
import com.example.finalproject.Instance.User;

import java.util.ArrayList;
import java.util.List;

public class RoomsManager {

    private RoomsAdapter mRoomAdapter;
    private List<Room> mRooms;

    public RoomsManager() {
        initListTemp();
        mRoomAdapter = new RoomsAdapter(mRooms);
    }

    public RoomsAdapter getRoomAdapter() {
        return mRoomAdapter;
    }

    private void initListTemp() {
        mRooms = new ArrayList<>();
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
        mRooms.add(new Room("123", "123"));
    }
}
