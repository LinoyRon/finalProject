package com.example.finalproject.ChatLogic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {

    private List<User> m_ChattingUsers;
    Context m_Context;

    public ConversationAdapter(List<User> m_ChattingUsers) {
        this.m_ChattingUsers = m_ChattingUsers;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.conversation_cell,parent,false);
        ConversationViewHolder songViewHolder = new ConversationViewHolder(view);

        m_Context = parent.getContext();

        return songViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {

        User currentUser = m_ChattingUsers.get(position);

        Glide.with(m_Context).load(currentUser.getPhotoPath()).into(holder.userProfilePic);
        holder.userName.setText(currentUser.getFirstName()+' '+currentUser.getLastName());
    }

    @Override
    public int getItemCount() { return m_ChattingUsers.size();  }

    protected class ConversationViewHolder extends RecyclerView.ViewHolder{

        ImageView userProfilePic;
        TextView userName;

        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);

            userProfilePic=itemView.findViewById(R.id.userProfileImageInChat);
            userName=itemView.findViewById(R.id.userNameInChat);
        }
    }
}
