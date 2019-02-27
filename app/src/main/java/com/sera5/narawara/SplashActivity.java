package com.sera5.narawara;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.Window;


import java.util.List;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_splash);
       // init();
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        },2000);
    }
}
