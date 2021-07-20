package com.example.myapplication;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity  {

    //Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MyApp", "MainActivity::onCreate");

        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_pane, new MySettingsFragment())
                .commit();

        //mContext = getApplicationContext();
        /*
        final Button button = findViewById(R.id.launch_secondary_activity);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Log.i("MyApp", "MainActivity onClick");

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setClass(getApplicationContext(), SecondaryActivity.class);
                // FLAG_ACTIVITY_NEW_TASK
                // :this activity will become the start of a new task on this history stack.
                // FLAG_ACTIVITY_NEW_TASK를 설정하지 않았을 경우 다음과 런타임 에러가 발생한다
                // ->Calling startActivity() from outside of an Activity context requires the FLAG_ACTIVITY_NEW_TASK flag
                // FLAG_ACTIVITY_REORDER_TO_FRONT
                // :this flag will cause the launched activity to be brought to the front of its task's history stack if it is already running
                // FLAG_ACTIVITY_SINGLE_TOP
                // : the activity will not be launched if it is already running at the top of the history stack
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                getApplicationContext().startActivity(intent);
            }
        });

         */
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MyApp", "MainActivity::onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MyApp", "MainActivity::onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MyApp", "MainActivity::onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MyApp", "MainActivity::onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MyApp", "MainActivity::onDestroy");
    }

}

















/*

MyApp myapp;

 */