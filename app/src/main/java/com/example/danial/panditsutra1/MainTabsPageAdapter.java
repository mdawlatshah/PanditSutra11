package com.example.danial.panditsutra1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.danial.panditsutra1.MainPageFiles.CallFragment;
import com.example.danial.panditsutra1.MainPageFiles.ChatFragment;
import com.example.danial.panditsutra1.MainPageFiles.StatusFragment;

class MainTabsPageAdapter extends FragmentPagerAdapter{
    private int numOfTabs;

    MainTabsPageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ChatFragment();
            case 1:
                return new StatusFragment();
            case 2:
                return new CallFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {return numOfTabs; }

}
