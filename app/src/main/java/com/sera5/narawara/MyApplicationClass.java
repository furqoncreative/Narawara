package com.sera5.narawara;

import android.support.multidex.MultiDexApplication;

import com.bugsnag.android.Bugsnag;
import com.facebook.drawee.backends.pipeline.Fresco;


public class MyApplicationClass extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
        Bugsnag.init(this);
        Fresco.initialize(this);
    }
}
