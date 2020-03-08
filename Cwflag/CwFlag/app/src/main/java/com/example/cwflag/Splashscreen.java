package com.example.cwflag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class Splashscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Timer().schedule(new TimerTask(){
            @Override
            public void run(){
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        },4000);
    }
}
