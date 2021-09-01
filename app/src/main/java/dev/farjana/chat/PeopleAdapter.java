package dev.farjana.chat;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Farjana on 1/14/2018.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    List<Contact> list;
    Context context;


    // Provide a reference to the views for each data item
// Complex data items may need more than one view per item, and
// you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        CircleImageView imageView;
        TextView name, contactNumber;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.profile_image);
            name = view.findViewById(R.id.nameTxt);
            contactNumber = view.findViewById(R.id.contactNumber);

        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public PeopleAdapter(List<Contact> list, Context context) {
        this.list = list;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactview, parent, false);

        final ViewHolder holder = new ViewHolder(itemView);
        itemView.setOnClickListener(
                view -> {
                    final int postion = holder.getAbsoluteAdapterPosition();
                    Intent intent = new Intent(context,MessageList.class);
                    intent.putExtra("people.name",list.get(postion).getNameTxt());
                    intent.putExtra("people.number",list.get(postion).getContactNumber());
                    intent.putExtra("people.image",list.get(postion).getImageId());
                    context.startActivity(intent);

                }
        );
        return holder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        holder.imageView.setImageResource(list.get(position).getImageId());
        holder.name.setText(list.get(position).getNameTxt());
        holder.contactNumber.setText(list.get(position).getContactNumber());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}