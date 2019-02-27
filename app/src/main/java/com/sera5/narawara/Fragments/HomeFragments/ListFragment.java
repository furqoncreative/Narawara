package com.sera5.narawara.Fragments.HomeFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sera5.narawara.MyFragment;
import com.sera5.narawara.R;

public class ListFragment extends MyFragment {

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_listview, container, false);

        return rootView;
    }
}