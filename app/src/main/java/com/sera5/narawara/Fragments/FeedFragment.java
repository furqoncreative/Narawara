package com.sera5.narawara.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sera5.narawara.BaseActivity;
import com.sera5.narawara.ListArtikel.Artikel;
import com.sera5.narawara.ListArtikel.ArtikelAdapter;
import com.sera5.narawara.MainActivity;
import com.sera5.narawara.MyFragment;
import com.sera5.narawara.R;
import com.sera5.narawara.SplashActivity;

import java.lang.ref.WeakReference;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class FeedFragment extends MyFragment {

    public List<Artikel> movieList = new ArrayList<>();
    public LinearLayout pb;
    public ArtikelAdapter mAdapter;
    public RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        SwipeRefreshLayout srl = view.findViewById(R.id.swiperefresh);

        mAdapter = new ArtikelAdapter(movieList, myContext);
         recyclerView = view.findViewById(R.id.recycler_view);
        new ArtikelEngine(this).execute();

        srl.setOnRefreshListener( () -> {
                    new ArtikelEngine(this).execute();
                    srl.setRefreshing(false);
                }
        );


        return view;
    }

    class ArtikelEngine extends AsyncTask<Void, Void, String> {

        private WeakReference<FeedFragment> activityReference;
        private Context c;

        // only retain a weak reference to the activity
        ArtikelEngine(FeedFragment context) {
            activityReference = new WeakReference<>(context);
            this.c = context.myContext;
        }

        @Override
        protected String doInBackground(Void... params) {
            final FeedFragment activity = activityReference.get();
            if (activity == null || activity.getActivity().isFinishing()) return null;

            DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("entry");

            if(!movieList.isEmpty()) movieList.clear();

            dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

                        String id = messageSnapshot.getKey();
                        String title = messageSnapshot.child("title").getValue(String.class);
                        String image_url = messageSnapshot.child("gambar").getValue(String.class);
                        String content = messageSnapshot.child("content").getValue(String.class);
                        String createdAt = messageSnapshot.child("createdAt").getValue(String.class);
                        String author = messageSnapshot.child("author").getValue(String.class);
                        //Object views = messageSnapshot.child("view").getValue();

                        if(image_url==null) image_url = "";

                        if(createdAt!=null)
                        movieList.add(new Artikel(id, title, image_url, content, createdAt,createdAt, author, "1", ""));

                        Collections.sort(activity.movieList, new Comparator<Artikel>() {
                            @Override
                            public int compare(Artikel d2, Artikel d1) {
                                return d1.getPublish_date().compareTo(d2.getPublish_date());
                            }
                        });
                    }


                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    if(recyclerView.getLayoutManager()==null) recyclerView.setLayoutManager(mLayoutManager);
                    if(recyclerView.getItemAnimator()==null) recyclerView.setItemAnimator(new DefaultItemAnimator());
                    if(recyclerView.getAdapter()==null) recyclerView.setAdapter(mAdapter);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            return "task finished";
        }

        @Override
        protected void onPostExecute(String result) {
            hideLoading();
        }

        @Override
        protected void onPreExecute() {
            showLoading(getActivity());
        }
    }

}