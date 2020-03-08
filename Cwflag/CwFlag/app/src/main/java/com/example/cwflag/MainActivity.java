package com.example.cwflag;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button bt1_GuessCon = (Button) findViewById(R.id.bt1_GuessCon);
        Button bt2_GuessHin = (Button) findViewById(R.id.bt2_GuessHin);
        Button bt3_GuessFlag = (Button) findViewById(R.id.bt3_GuessFlag);
        Button bt4_Advanced = (Button) findViewById(R.id.bt4_Advanced);

        //going to the button class
        bt1_GuessCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int1 = new Intent(MainActivity.this, Guesscountry.class);
                startActivity(int1);
            }
        });
        bt2_GuessHin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int2 = new Intent(MainActivity.this, Guesshin.class);
                startActivity(int2);
            }
        });
        bt3_GuessFlag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int3 = new Intent(MainActivity.this, Guessflag.class);
                startActivity(int3);
            }
        });
        bt4_Advanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent int4 = new Intent(MainActivity.this, Advanced_lvl.class);
                startActivity(int4);
            }
        });
    }
}
