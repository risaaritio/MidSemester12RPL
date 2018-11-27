package com.example.rplrus25.midsemester12rpl;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;


    public PageAdapter(FragmentManager fm, int mNumOfTabs) {
        super(fm);
        this.mNumOfTabs= mNumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                fragment_nowplaying tab1 = new fragment_nowplaying();
                return tab1;
            case 1:
                fragment_upcoming tab2 = new fragment_upcoming();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
