package dev.farjana.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Farjana on 1/13/2018.
 */

public class AdapterClass extends Adapter<AdapterClass.ViewHolder> {
    List<Data> list;
    Context context;



    public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name,lastText,time;
        LinearLayout chatLayout;

        public ViewHolder(View view) {
            super(view);
            chatLayout = view.findViewById(R.id.chatLayout);
            imageView = view.findViewById(R.id.profile_image);
            name = view.findViewById(R.id.nameTxt);
            lastText = view.findViewById(R.id.lastTxt);
            time = view.findViewById(R.id.timeTxt);

        }
    }

    public AdapterClass(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatview, parent, false);
        final ViewHolder holder= new ViewHolder(itemView);

        itemView.setOnClickListener(
                view -> {
                    int position = holder.getAbsoluteAdapterPosition();
                    Intent intent = new Intent(context,MessageList.class);
                    intent.putExtra("people.name",list.get(position).nameTxt);
                    intent.putExtra("people.image",list.get(position).imageId);
                    context.startActivity(intent);
                }
        );
        return holder;
    }

     @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).imageId);
        holder.name.setText(list.get(position).nameTxt);
        holder.lastText.setText(list.get(position).lastTxt);
        holder.time.setText(list.get(position).time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}