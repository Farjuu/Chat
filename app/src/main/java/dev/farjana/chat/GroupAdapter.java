package dev.farjana.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Farjana on 1/14/2018.
 */

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
        List<GroupData> list;
        Context context;


public static class ViewHolder extends RecyclerView.ViewHolder {

    CircleImageView imageView;
    TextView name, lastText, time;

    public ViewHolder(View view) {
        super(view);
        imageView = view.findViewById(R.id.profile_image);
        name = view.findViewById(R.id.nameTxt);

    }
}

    public GroupAdapter(List<GroupData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.groupview, parent, false);

        return new ViewHolder(itemView);
    }

     @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.imageView.setImageResource(list.get(position).imageId);
        holder.name.setText(list.get(position).nameTxt);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
