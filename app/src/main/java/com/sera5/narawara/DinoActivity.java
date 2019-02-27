package com.sera5.narawara;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DinoActivity extends BaseActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dino);

        ActionBar actionBar = getActionBar();
        android.support.v7.app.ActionBar actionBar1 = getSupportActionBar();

        if(actionBar1!=null) {
            actionBar1.setDisplayHomeAsUpEnabled(true);
        } else {
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }

        final WebView webView = (WebView) findViewById(R.id.wv);


        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);

        String folderPath = "file:android_asset/dino/";
        String fileName = "index.html";
        String file = folderPath + fileName;

        webView.loadUrl(file);

    }
}
