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



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
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

    // Provide a suitable constructor (depends on the kind of dataset)
    public AdapterClass(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
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
                    context.startActivity(intent);
                }
        );
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.imageView.setImageResource(list.get(position).imageId);
        holder.name.setText(list.get(position).nameTxt);
        holder.lastText.setText(list.get(position).lastTxt);
        holder.time.setText(list.get(position).time);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}