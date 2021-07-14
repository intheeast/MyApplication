package com.example.myapplication;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        Log.i("MyApp", "SecondaryActivity::onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("MyApp", "SecondaryActivity::onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i("MyApp", "SecondaryActivity::onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i("MyApp", "SecondaryActivity::onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.i("MyApp", "SecondaryActivity::onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("MyApp", "SecondaryActivity::onResume");
    }
}