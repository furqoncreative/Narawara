package com.sera5.narawara;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;


public class BaseActivity extends AppCompatActivity {

    private ProgressDialog pDialog;
    public UserClass uc;

    public AtomicReference<FirebaseDatabase> fbdb = new AtomicReference<>(FirebaseDatabase.getInstance());

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        uc = new UserClass(this);
    }

    public boolean loadFragment(MyFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }

        return false;
    }
    public String convertTime(long time) {
        Date date = new Date(time);
        @SuppressLint("SimpleDateFormat")
        Format format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return format.format(date);
    }

    FirebaseDatabase getfdb() {
        return fbdb.get();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}