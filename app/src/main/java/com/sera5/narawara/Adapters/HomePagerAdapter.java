package com.sera5.narawara.Adapters;

import android.content.Context;
import android.icu.text.UnicodeSetIterator;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sera5.narawara.Fragments.HomeFragments.ListFragment;
import com.sera5.narawara.Fragments.HomeFragments.MapFragment;

import com.sera5.narawara.R;

public class HomePagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public HomePagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    // This determines the fragment for each tab
    @Override
    public Fragment getItem(int position) {
        System.out.println("pos: "+position);
        if (position == 0) {
            return new ListFragment();
        } else if (position == 1){
            return new MapFragment();
        } else {
            return new ListFragment();
        }
    }

    // This determines the number of tabs
    @Override
    public int getCount() {
        return 2;
    }

    // This determines the title for each tab
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        switch (position) {
            case 0:
                return mContext.getString(R.string.home_tab_list);
            case 1:
                return mContext.getString(R.string.home_tab_map);
            default:
                return null;
        }
    }

}