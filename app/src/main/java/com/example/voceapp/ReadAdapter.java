package com.example.voceapp;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ReadAdapter extends FragmentPagerAdapter {

    //adapter for switching between the tabs
    private Context context;
    int totalTabs;

    @Override
    public int getCount() {
        return totalTabs;
    }

    public ReadAdapter(FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                type_layout_fragment typeLayoutFragment = new type_layout_fragment();
            return typeLayoutFragment;
            case 1:
                age_layout_fragment ageLayoutFragment = new age_layout_fragment();
                return ageLayoutFragment;
            default:
                return null;
        }
    }
}
