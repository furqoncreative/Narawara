package com.sera5.narawara;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class MyFragment extends Fragment {
    public FragmentActivity myContext;
    public UserClass uc;
    private ProgressDialog pDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        uc = new UserClass(getActivity());
        // Code here
    }

    public void showLoading(Context c) {
        pDialog = buildProgress(c);
        pDialog.show();
    }

    private ProgressDialog buildProgress(Context c) {
        ProgressDialog spDialog = new ProgressDialog(c);
        spDialog.setMessage("Please wait...");
        spDialog.setCancelable(false);
        return spDialog;
    }

    public void hideLoading() {
        if (pDialog != null) {
            if (pDialog.isShowing())
                pDialog.dismiss();
        }
    }


    public String convertTime(long time) {
        Date date = new Date(time);
        @SuppressLint("SimpleDateFormat")
        Format format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return format.format(date);
    }
}
