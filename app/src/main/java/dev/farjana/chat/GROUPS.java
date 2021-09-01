package dev.farjana.chat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farjana on 1/11/2018.
 */

public class GROUPS extends Fragment {

    View view3;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view3 = inflater.inflate(R.layout.groupslayout,container,false);
        RecyclerView recyclerView = view3.findViewById(R.id.my_recycler_view);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);

        GroupData a = new GroupData(R.mipmap.cat3,"Pufi");

        final List<GroupData> data = new ArrayList<>();
        data.add(a);

        RecyclerView.Adapter<GroupAdapter.ViewHolder> adapter = new GroupAdapter(data, getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        return view3;
    }


}