package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;


public class MyAppUtils {


    public static void setSharedPreference(SharedPreferences prefs, String key, String value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
        //editor.commit(); // for test
    }

    public static void setSharedPreference(SharedPreferences prefs, String key, boolean value) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
        //editor.commit(); // for test
    }

    public static SharedPreferences getSharedPreferences(Context context, String prefsName) {
        return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE);
    }
}
