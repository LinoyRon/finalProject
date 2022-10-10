package com.example.finalproject.ChatLogic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ConversationViewHolder> {

    private List<Message> m_ChatHistory;
    Context m_Context;

    public ChatAdapter(List<User> m_ChattingUsers) {
        this.m_ChatHistory = m_ChatHistory;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message,parent,false);
        ConversationViewHolder ViewHolder = new ConversationViewHolder(view);

        m_Context = parent.getContext();

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {

        Message currentMessage = m_ChatHistory.get(position);

        //Glide.with(m_Context).load(currentUser.getPhotoPath()).into(holder.userProfilePic);
        holder.userName.setText("name name");
    }

    @Override
    public int getItemCount() { return m_ChatHistory.size();  }

    protected class ConversationViewHolder extends RecyclerView.ViewHolder{

        ImageView userProfilePic;
        TextView userName;

        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);

            userProfilePic=itemView.findViewById(R.id.userProfileImageInChat);
            userName=itemView.findViewById(R.id.userNameInChat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "HI", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
