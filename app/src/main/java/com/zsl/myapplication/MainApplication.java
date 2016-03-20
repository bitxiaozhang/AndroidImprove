package com.zsl.myapplication;

import android.app.Application;

/**
 * Created by zsl on 3/15/16.
 */
public class MainApplication extends Application {
    private static MainApplication ourInstance = null;

    public static MainApplication getInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }
}
