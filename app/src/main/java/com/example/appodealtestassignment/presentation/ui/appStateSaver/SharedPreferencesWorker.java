package com.example.appodealtestassignment.presentation.ui.appStateSaver;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

public class SharedPreferencesWorker implements ISharedPreferencesWorker{
    private final String APP_RUN_COUNT = String.valueOf("APP_RUN_COUNT".hashCode());
    private SharedPreferences activityPreferences;

    public SharedPreferencesWorker(Activity context){
        // Создайте или извлеките объект настроек активности.
        activityPreferences = context.getPreferences(Activity.MODE_PRIVATE);
        // Извлеките редактор, чтобы изменить Общие настройки.
    }
    public void saveAppState(int appState){
        SharedPreferences.Editor editor = activityPreferences.edit();
        editor.putInt(APP_RUN_COUNT, appState);
        editor.apply();
    }

    public int getAppState(){
        return activityPreferences.getInt(APP_RUN_COUNT, 0);
    }

}
