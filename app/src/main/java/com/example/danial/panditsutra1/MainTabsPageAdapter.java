package com.example.danial.panditsutra1;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.danial.panditsutra1.MainPageFiles.PanditsFragment;
import com.example.danial.panditsutra1.MainPageFiles.KundliFragment;
import com.example.danial.panditsutra1.MainPageFiles.OtherFragment;

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
                return new PanditsFragment();
            case 1:
                return new KundliFragment();
            case 2:
                return new OtherFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {return numOfTabs; }

}
