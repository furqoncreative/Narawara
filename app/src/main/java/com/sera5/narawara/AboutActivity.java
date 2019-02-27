package com.sera5.narawara;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.DraweeView;

@SuppressLint("Registered")
public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        String[][] creditsArray = {
                {"OkHttp", "http://square.github.io/okhttp"},
                {"Bugsnag", "http://bugsnag.com"},
                {"Fresco Image Library", "http://frescolib.org"},
                {"HTMLTextView", "https://github.com/PrivacyApps/html-textview"},
                {"MaterialSearchView", "https://github.com/MiguelCatalan/MaterialSearchView"},
                {"Essential set icon  by user9875879", "https://www.flaticon.com/packs/essential-set-2"},
                {"Josytick Icon", "https://www.flaticon.com/packs/essential-set-2"},
                {"FancyButtons", "https://github.com/medyo/Fancybuttons"},
                {"Map Marker image by freepik", "https://www.flaticon.com/authors/freepik"},
                {"FreeTime Icon", "https://www.flaticon.com/packs/free-time-11"},
        };

        TextView ver = findViewById(R.id.ver_number);
        DraweeView logo = findViewById(R.id.about_logo);

        ActionBar actionBar = getActionBar();
        android.support.v7.app.ActionBar actionBar1 = getSupportActionBar();

        if(actionBar1!=null) {
            actionBar1.setDisplayHomeAsUpEnabled(true);
            setTitle("Tentang Aplikasi Ini");
        } else {
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            setTitle("Tentang Aplikasi Ini");
        }

        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            String version = pInfo.versionName;
            ver.setText(version);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        LinearLayout ll = findViewById(R.id.third_party_credits);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        for (String[] a : creditsArray) {
            TextView textView = new TextView(this);
            String b = a[0] + " &lt;<a href=" + a[1] + ">" + a[1] + "</a>&gt;";
            textView.setText(Html.fromHtml(b));
            textView.setLayoutParams(params);
            textView.setPadding(4, 0, 0, 0);
            ll.addView(textView);
        }

        logo.setOnLongClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DinoActivity.class);
            startActivity(intent);
            return false;
        });
    }
}
