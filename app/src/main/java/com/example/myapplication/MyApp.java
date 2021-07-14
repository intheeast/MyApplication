package com.example.myapplication;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.util.Log;

public class MyApp extends Application implements Application.ActivityLifecycleCallbacks {

    final static String mainactivityname = MainActivity.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        //MySettingsFragment.setDefaultValues(this);

        registerActivityLifecycleCallbacks(this);

        Log.i("MyApp", "MyApp::onCreate");
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        Log.i("MyApp", "MyApp::onActivityCreated");
        //Log.i("MyApp", activity.getComponentName().toString());
        //Log.i("MyApp", activity.getComponentName().toShortString());

        switch (activity.getClass().getSimpleName()) {
            case "MainActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "MainActivity Class Created!!!");
                Log.i("MyApp", "****************************************");
                break;

            //추가된 코드!!!
            case "SecondaryActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "SecondaryActivity Class Created!!!");
                Log.i("MyApp", "****************************************");
                break;

            default:
                Log.i("MyApp", "what???");
                break;
        }

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        Log.i("MyApp", "MyApp::onActivityStarted");

        switch (activity.getClass().getSimpleName()) {
            case "MainActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "MainActivity Class Started!!!");
                Log.i("MyApp", "****************************************");
                break;

            case "SecondaryActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "SecondaryActivity Class Started!!!");
                Log.i("MyApp", "****************************************");
                break;

            default:
                Log.i("MyApp", "what???");
                break;
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        Log.i("MyApp", "MyApp::onActivityResumed");

        switch (activity.getClass().getSimpleName()) {
            case "MainActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "MainActivity Class Resumed!!!");
                Log.i("MyApp", "****************************************");
                break;

            case "SecondaryActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "SecondaryActivity Class Resumed!!!");
                Log.i("MyApp", "****************************************");
                break;

            default:
                Log.i("MyApp", "what???");
                break;
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        Log.i("MyApp", "MyApp::onActivityPaused");

        switch (activity.getClass().getSimpleName()) {
            case "MainActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "MainActivity Class Paused!!!");
                Log.i("MyApp", "****************************************");
                break;

            case "SecondaryActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "SecondaryActivity Class Paused!!!");
                Log.i("MyApp", "****************************************");
                break;

            default:
                Log.i("MyApp", "what???");
                break;
        }

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        Log.i("MyApp", "MyApp::onActivityStopped");

        switch (activity.getClass().getSimpleName()) {
            case "MainActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "MainActivity Class Stopped!!!");
                Log.i("MyApp", "****************************************");
                break;

            case "SecondaryActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "SecondaryActivity Class Stopped!!!");
                Log.i("MyApp", "****************************************");
                break;

            default:
                Log.i("MyApp", "what???");
                break;
        }
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
        Log.i("MyApp", "MyApp::onActivitySaveInstanceState");

        switch (activity.getClass().getSimpleName()) {
            case "MainActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "MainActivity Class SaveInstanceState!!!");
                Log.i("MyApp", "****************************************");
                break;

            case "SecondaryActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "SecondaryActivity Class SaveInstanceState!!!");
                Log.i("MyApp", "****************************************");
                break;

            default:
                Log.i("MyApp", "what???");
                break;
        }
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        Log.i("MyApp", "MyApp::onActivityDestroyed");

        switch (activity.getClass().getSimpleName()) {
            case "MainActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "MainActivity Class Destroyed!!!");
                Log.i("MyApp", "****************************************");
                break;

            case "SecondaryActivity":
                Log.i("MyApp", "****************************************");
                Log.i("MyApp", "SecondaryActivity Class Destroyed!!!");
                Log.i("MyApp", "****************************************");
                break;

            default:
                Log.i("MyApp", "what???");
                break;
        }
    }

}
