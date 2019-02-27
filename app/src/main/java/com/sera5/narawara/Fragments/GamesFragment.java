package com.sera5.narawara.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.sera5.narawara.MyFragment;
import com.sera5.narawara.QuizActivity;
import com.sera5.narawara.R;

public class GamesFragment extends MyFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_games, container, false);

        Button q = view.findViewById(R.id.quiz_start);

        q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });

        return view;
    }
    public void updateDetail() {
        Intent intent = new Intent(getActivity(), QuizActivity.class);
        startActivity(intent);
    }
}