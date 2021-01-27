package com.example.appodealtestassignment;


import android.app.Application;

public class AndroidApplication extends Application {
    private static AndroidApplication instance = null;

    public static AndroidApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
