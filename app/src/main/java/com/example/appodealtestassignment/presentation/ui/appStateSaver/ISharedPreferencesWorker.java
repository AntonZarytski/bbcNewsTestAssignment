package com.example.appodealtestassignment.presentation.ui.appStateSaver;

import android.content.SharedPreferences;

public interface ISharedPreferencesWorker {
    public void saveAppState(int appState);

    public int getAppState();
}
