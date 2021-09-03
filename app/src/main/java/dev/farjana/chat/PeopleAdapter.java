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

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Farjana on 1/14/2018.
 */

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.ViewHolder> {
    List<Contact> list;
    Context context;


  public static class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView imageView;
        TextView name, contactNumber;
        LinearLayout peoplelayout;
        public ViewHolder(View view) {
            super(view);
            peoplelayout = view.findViewById(R.id.chatLayout);
            imageView = view.findViewById(R.id.profile_image);
            name = view.findViewById(R.id.nameTxt);
            contactNumber = view.findViewById(R.id.contactNumber);

        }
    }

    public PeopleAdapter(List<Contact> list, Context context) {
        this.list = list;
        this.context = context;
    }

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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.imageView.setImageResource(R.mipmap.cat);
        holder.name.setText(list.get(position).getNameTxt());
        holder.contactNumber.setText(list.get(position).getContactNumber());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}