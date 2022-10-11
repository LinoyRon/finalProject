package com.example.finalproject.RoomsLogic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.Room;
import com.example.finalproject.R;

import java.util.List;

public class RoomsAdapter extends RecyclerView.Adapter<RoomsAdapter.ConversationViewHolder> {

    private List<Room> mRooms;
    Context m_Context;

    public RoomsAdapter(List<Room> mRooms) {
        this.mRooms = mRooms;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_cell,parent,false);
        ConversationViewHolder ViewHolder = new ConversationViewHolder(view);

        m_Context = parent.getContext();

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        Room currentRoom = mRooms.get(position);

        holder.roomNumber.setText(currentRoom.getRoomNumber()+", "+currentRoom.getRoomFloor());
        if(currentRoom.isAvailable())
        {
            holder.roomOwner.setText(currentRoom.getOwner().getUserFullName());
        }
        else{
            holder.roomOwner.setVisibility(View.GONE); //or message like room is free
        }
    }

    @Override
    public int getItemCount() { return mRooms.size();  }

    protected class ConversationViewHolder extends RecyclerView.ViewHolder{

        TextView roomNumber, roomOwner;

        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);

            roomNumber=itemView.findViewById(R.id.roomNumber);
            roomOwner=itemView.findViewById(R.id.roomOwnerName);
        }
    }
}
