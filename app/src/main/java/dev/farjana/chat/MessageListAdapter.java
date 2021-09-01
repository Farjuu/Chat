package dev.farjana.chat;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by Farjana on 1/14/2018.
 */

public class MessageListAdapter extends RecyclerView.Adapter<MessageListAdapter.ViewHolder> {

    Context context;
    List<BaseMessage> messageList;

    public MessageListAdapter(List<BaseMessage> messageList,Context context) {
        this.context = context;
        this.messageList = messageList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.received_sms, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.time.setText(messageList.get(position).time);
        holder.text_message_body.setText(messageList.get(position).textbody);

        if (messageList.get(position).ismine){
            holder.smslayout.setGravity(Gravity.END);
        }else {
            holder.smslayout.setGravity(Gravity.START);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView text_message_body,time;
        LinearLayout smslayout ;


        public ViewHolder(View view) {
            super(view);
            smslayout = view.findViewById(R.id.sms_layout);

            text_message_body = view.findViewById(R.id.text_message_body);
            time = view.findViewById(R.id.text_message_time);

        }
    }

}