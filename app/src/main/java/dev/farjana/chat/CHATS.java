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

public class CHATS extends Fragment {

    View view2;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view2 = inflater.inflate(R.layout.chatslayout,container,false);
        RecyclerView recyclerView = view2.findViewById(R.id.my_recycler_view);
         recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setLayoutManager(layoutManager);

        // Demo data added....

        Data a = new Data(R.mipmap.cat,"Shushi","I want fish","12:35");
        Data b = new Data(R.mipmap.cat2,"Minuu","what do u do?","12:30");
        Data C = new Data(R.mipmap.cat3,"Pufi","meaw","12:20");

        final List<Data> data = new ArrayList<>();
        data.add(a);
        data.add(b);
        data.add(C);
        AdapterClass adapter = new AdapterClass(data, getContext());
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        return view2;
    }


}