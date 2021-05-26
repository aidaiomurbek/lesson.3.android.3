package com.example.lesson3android3;

import android.app.Application;

import com.example.lesson3android3.data.preferences.PreferenceUtils;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtils.init(this);
    }
}
