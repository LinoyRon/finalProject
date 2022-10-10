package com.example.finalproject.ConversationLogic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.Instance.Conversation;
import com.example.finalproject.Instance.User;
import com.example.finalproject.R;

import java.util.List;

public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {

    private List<User> m_ChattingUsers;
    Context m_Context;

    public ConversationAdapter(List<User> m_ChattingUsers) {
        this.m_ChattingUsers = m_ChattingUsers;
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

        User currentUser = m_ChattingUsers.get(position);

        /*Glide.with(m_Context).load(currentUser.getPhotoPath()).into(holder.userProfilePic);
        holder.userName.setText(currentUser.getFirstName()+' '+currentUser.getLastName());

        Conversation conversation = getItem(position);
        Glide.with(holder.itemView.getContext())
                .load(conversation.getOppositeUser().getPhotoPath())
                .circleCrop()
                .into(holder.thumbnail);
        holder.fullName.setText(conversation.getOppositeUser().userFullName());
        holder.itemView.setOnClickListener(v-> {
            listener.onConversationClick(conversation);
        });*/
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //openChatActivity(conversation.getConversationId(), conversation.getChatPartner() );
                    Navigation.findNavController(view).navigate(R.id.action_conversationFragment_to_chatFragment);
                }
            });
        }
    }
}
