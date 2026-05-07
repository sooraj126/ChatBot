package com.example.chatbot;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<chats> messageList;
    private String username;
    public MessageAdapter(List<chats> messageList) {
        this.messageList = messageList;

    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        chats message = messageList.get(position);

        holder.tvMessageText.setText(message.getMessageText());
        holder.tvMessageTime.setText(message.getTimestamp());

        if (message.getSender().equals("user")) {
            holder.messageContainer.setGravity(Gravity.END);
            holder.bubbleLayout.setBackgroundColor(Color.parseColor("#34E234"));
            holder.tvMessageText.setTextColor(Color.parseColor("#0D0F14"));
            holder.tvMessageTime.setTextColor(Color.parseColor("#000000"));
            holder.tvUser.setVisibility(View.VISIBLE);
            holder.tvAI.setVisibility(View.GONE);

        } else {
            holder.messageContainer.setGravity(Gravity.START);
            holder.bubbleLayout.setBackgroundColor(Color.parseColor("#3453F0"));
            holder.tvMessageText.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvMessageTime.setTextColor(Color.parseColor("#FFFFFF"));
            holder.tvUser.setVisibility(View.GONE);
            holder.tvAI.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {

        TextView tvMessageText, tvMessageTime ,tvUser , tvAI;
        LinearLayout messageContainer,bubbleLayout;

        public MessageViewHolder(@NonNull View itemView) {
            super(itemView);

            tvMessageText = itemView.findViewById(R.id.tvMessageText);
            tvMessageTime = itemView.findViewById(R.id.tvMessageTime);
            messageContainer = itemView.findViewById(R.id.messageContainer);
            bubbleLayout = itemView.findViewById(R.id.bubbleLayout);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvAI = itemView.findViewById(R.id.tvAI);

        }
    }
}