package dev.farjana.chat;

/*
 * Created by Farjana on 1/11/2018.
 */


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Farjana on 11/3/2017.
 */

class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> list = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    void addMyFragment(Fragment f,String title){
        list.add(f);
        titles.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return titles.get(position);
    }
}
