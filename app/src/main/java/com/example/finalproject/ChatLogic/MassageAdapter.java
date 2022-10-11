package com.example.finalproject.ChatLogic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalproject.Instance.Message;
import com.example.finalproject.R;

import java.util.List;

public class MassageAdapter extends RecyclerView.Adapter<MassageAdapter.ConversationViewHolder> {

    private List<Message> mChatHistory;
    Context m_Context;

    public MassageAdapter(List<Message> mChatHistory) {
        this.mChatHistory = mChatHistory;
    }

    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_cell,parent,false);
        ConversationViewHolder ViewHolder = new ConversationViewHolder(view);

        m_Context = parent.getContext();

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        Message currentMessage = mChatHistory.get(position);

        if (currentMessage.getIsSent()) {
            holder.bindSentMessage();
        } else {
            holder.bindReceivedMessage();
        }

        holder.massageContentTv.setText(currentMessage.getMessage());
    }

    @Override
    public int getItemCount() { return mChatHistory.size();  }

    protected class ConversationViewHolder extends RecyclerView.ViewHolder{

        TextView massageContentTv;
        View mItemView;

        public ConversationViewHolder(@NonNull View itemView) {
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
