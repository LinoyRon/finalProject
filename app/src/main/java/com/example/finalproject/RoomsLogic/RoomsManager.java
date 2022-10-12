package com.example.finalproject.RoomsLogic;

import com.example.finalproject.ChatLogic.MassageAdapter;
import com.example.finalproject.Firebase.RoomsRepository;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.Room;
import com.example.finalproject.Instance.User;

import java.util.ArrayList;
import java.util.List;

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