package com.example.finalproject.Rooms;

import com.example.finalproject.Instance.Room;

import java.util.ArrayList;

public class RoomsManager {
    private static RoomsManager mInstance = new RoomsManager();
    private RoomsRepository mRoomsRepository;
    private RoomsAdapter mRoomAdapter;
    private ArrayList<Room> mRoomsList;

    public static RoomsManager getInstance(){ return mInstance; }

    public RoomsManager() {
        mRoomsRepository=new RoomsRepository();
        mRoomsList = mRoomsRepository.getRoomList();
        mRoomAdapter = new RoomsAdapter(mRoomsList);
    }

    public RoomsRepository getRoomsRepository() { return mRoomsRepository; }

    public RoomsAdapter getRoomAdapter() { return mRoomAdapter;  }

    public ArrayList<Room> getRoomsList() {
        mRoomsList = mRoomsRepository.getRoomList();
        mRoomAdapter.notifyDataSetChanged();

        return mRoomsList;
    }
}