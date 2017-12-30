package com.sjpproj1.sjp1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;

import android.os.Handler;

public class SecondActivity extends Activity {

     Handler handle =new Handler();

    private static int TIME_OUT = 5000; //Time to launch the another activity
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final View myLayout = findViewById(R.id.start);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }

});