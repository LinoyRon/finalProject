package com.example.finalproject.ConversationLogic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.Instance.Conversation;
import com.example.finalproject.R;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {

    private List<Conversation> mChattingUsers;
    Conversation mCurrentConversation;
    ConversationAdapterListener listener;
    Context m_Context;

    public ConversationAdapter(ConversationAdapterListener iListener,List<Conversation> iChattingUsers) {
        this.mChattingUsers = iChattingUsers;
        this.listener = iListener;
    }

    public interface ConversationAdapterListener {
        void onConversationClick(Conversation conversation);
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
        mCurrentConversation = mChattingUsers.get(position);

        holder.userName.setText(mCurrentConversation.getChatPartner().getFullName());
        //holder.lastMessage.setText(mCurrentConversation.getLastMessage().getMessageContent());

        Glide.with(holder.itemView.getContext())
                .load(mCurrentConversation.getChatPartner().getPhotoPath())
                .into(holder.userProfilePic);
    }

    @Override
    public int getItemCount() { return mChattingUsers.size();  }

    public class ConversationViewHolder extends RecyclerView.ViewHolder{

        ImageView userProfilePic;
        TextView userName, lastMessage;

        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);

            userProfilePic=itemView.findViewById(R.id.userProfileImageInChat);
            userName=itemView.findViewById(R.id.userNameInChat);
            lastMessage=itemView.findViewById(R.id.inputLastName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onConversationClick(mCurrentConversation);
                }
            });
        }
    }
}
