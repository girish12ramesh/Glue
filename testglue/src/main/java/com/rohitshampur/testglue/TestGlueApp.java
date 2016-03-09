package com.rohitshampur.testglue;

import android.app.Application;

import com.rohitshampur.glue.Glue;

/**
 * Created by rohit on 9/3/16.
 */
public class TestGlueApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Glue.init(R.id.class);
    }
}
