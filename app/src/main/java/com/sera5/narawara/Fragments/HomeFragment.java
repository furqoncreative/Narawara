package com.sera5.narawara.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sera5.narawara.Adapters.HomePagerAdapter;
import com.sera5.narawara.MyFragment;
import com.sera5.narawara.R;

public class HomeFragment extends MyFragment {

    HomePagerAdapter adapter = null;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

                // Find the view pager that will allow the user to swipe between fragments
        viewPager = view.findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        adapter = new HomePagerAdapter(getContext(), getChildFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        // Give the TabLayout the ViewPager
        tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

}