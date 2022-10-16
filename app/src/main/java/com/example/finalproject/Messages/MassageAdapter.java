package com.example.finalproject.Messages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalproject.Firebase.Authentication;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MassageAdapter extends RecyclerView.Adapter<MassageAdapter.MessageViewHolder> {

    private List<Message> mChatHistory = new ArrayList<>();
    private Message mCurrentMessage;
    Context m_Context;

    public MassageAdapter(List<Message> mChatHistory) {
        this.mChatHistory = mChatHistory;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_cell,parent,false);
        MessageViewHolder ViewHolder = new MessageViewHolder(view);

        m_Context = parent.getContext();

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        mCurrentMessage = mChatHistory.get(position);

        if(isCurrentUserSentTheMessage()){
            holder.bindSentMessage();
        } else {
            holder.bindReceivedMessage();
        }

        holder.massageContentTv.setText(mCurrentMessage.getMessageContent());
    }

    private boolean isCurrentUserSentTheMessage() {
        return Objects.equals(mCurrentMessage.getSender().getID(), Authentication.getInstance().getLoggedInUser().getID());
    }

    @Override
    public int getItemCount() { return mChatHistory.size();  }

    protected class MessageViewHolder extends RecyclerView.ViewHolder{

        TextView massageContentTv;
        View mItemView;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView=itemView;
            massageContentTv=itemView.findViewById(R.id.itemMessageContentSent);
        }

        private void bindReceivedMessage() {
            ((LinearLayout)mItemView.findViewById(R.id.itemMessageLayoutSent)).setVisibility(View.GONE);
            ((LinearLayout)mItemView.findViewById(R.id.itemMessageLayoutReceived)).setVisibility(View.VISIBLE);
            massageContentTv = mItemView.findViewById(R.id.itemMessageContentReceived);
        }

        private void bindSentMessage() {
            ((LinearLayout)mItemView.findViewById(R.id.itemMessageLayoutSent)).setVisibility(View.VISIBLE);
            ((LinearLayout)mItemView.findViewById(R.id.itemMessageLayoutReceived)).setVisibility(View.GONE);
            massageContentTv = mItemView.findViewById(R.id.itemMessageContentSent);
        }
    }
}
