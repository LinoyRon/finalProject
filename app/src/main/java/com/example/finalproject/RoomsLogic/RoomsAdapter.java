package com.example.finalproject.RoomsLogic;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.Room;
import com.example.finalproject.R;

import java.util.List;

interface ItemTouchHelperAdapter { void OnItemSwiped(int i_Position); }

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>
        implements ItemTouchHelperAdapter{

    private static MyRoomListener myRoomListener;
    private List<Room> mRooms;
    Context m_Context;

    @Override
    public void OnItemSwiped(int i_Position) {
        //MainActivity.m_DeleteDialog.setPosition(i_Position);
        //MainActivity.m_DeleteDialog.show();
    }

    public interface MyRoomListener {
        void onRoomClicked(Room clickedRoom, TextView ownerName, ImageView roomStatusImage);
    }

    public static void setMyRoomListener(MyRoomListener iRoomListener) {
        myRoomListener = iRoomListener;
    }

    public RoomsAdapter(List<Room> mRooms) {
        this.mRooms = mRooms;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_cell,parent,false);
        RoomViewHolder ViewHolder = new RoomViewHolder(view);

        m_Context = parent.getContext();

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room currentRoom = mRooms.get(position);

        holder.roomNumber.setText(currentRoom.getRoomNumber()+", "+currentRoom.getRoomFloor());
        holder.roomOwner.setText(R.string.roomReservedBy+" "+ Authentication.getLoggedInUser().getUserFullName());
    }

    @Override
    public int getItemCount() { return mRooms.size();  }

    protected class RoomViewHolder extends RecyclerView.ViewHolder
            implements GestureDetector.OnGestureListener{

        TextView roomNumber, roomOwner;
        ImageView roomStatusImage;
        private GestureDetector gestureDetector;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            roomNumber=itemView.findViewById(R.id.roomNumber);
            roomOwner=itemView.findViewById(R.id.roomOwnerName);
            roomStatusImage=itemView.findViewById(R.id.roomStatusImage);
            gestureDetector = new GestureDetector(itemView.getContext(), this);

            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    gestureDetector.onTouchEvent(motionEvent);

                    return true;
                }
            });
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            if (myRoomListener != null){
                myRoomListener.onRoomClicked(mRooms.get(getAdapterPosition()),roomOwner,roomStatusImage);
            }

            return true;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            return true;
        }
    }
}
