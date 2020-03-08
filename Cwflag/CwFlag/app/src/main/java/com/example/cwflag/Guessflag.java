package com.example.cwflag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Guessflag extends AppCompatActivity {

    ImageView guessflagimage1, guessflagimage2, guessflagimage3, correctFlag;
    TextView txt_statusofAnswer, txt_nameofCountry;
    Button button2;

    Random r;
    Database dbase;
    CountryItem currentCountry;

    ArrayList<CountryItem> countryflag_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessflag);


        //identify the ids of the button,images,textview
        button2 = findViewById(R.id.button2);
        guessflagimage1 = findViewById(R.id.guessflagimage1);
        guessflagimage2 = findViewById(R.id.guessflagimage2);
        guessflagimage3 = findViewById(R.id.guessflagimage3);
        txt_statusofAnswer = findViewById(R.id.txt_statusofAnswer);
        txt_nameofCountry = findViewById(R.id.txt_nameofCountry);

        r = new Random();

        dbase = new Database();

        countryflag_list = new ArrayList();

        //add the flags and answers to a arraylist
        for (int i = 0; i < dbase.answers.length; i++) {
            CountryItem m = new CountryItem(dbase.answers[i], dbase.flags[i]);
            countryflag_list.add(m);
        }
        //shuffle the country name arraylist
        Collections.shuffle(countryflag_list);

        //calling a method
        loadCountry();


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCountry();
            }
        });

        //on click images and calling the methods
        guessflagimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifCorrect(guessflagimage1);
            }
        });

        guessflagimage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifCorrect(guessflagimage2);
            }
        });

        guessflagimage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifCorrect(guessflagimage3);
            }
        });
    }

    //creating a new method
    void loadCountry() {
        txt_statusofAnswer.setText("");
        int i = r.nextInt(countryflag_list.size());
        int ii = r.nextInt(countryflag_list.size());
        int iii = r.nextInt(countryflag_list.size());
        int iv = r.nextInt(3);
        currentCountry = countryflag_list.get(i);

        //setting the flags to imageviews
        while (ii == i || iii == i) {
            ii = r.nextInt(countryflag_list.size());
            iii = r.nextInt(countryflag_list.size());
        }

        if (iv == 1) {
            guessflagimage1.setImageResource(currentCountry.getImage());
            guessflagimage2.setImageResource(countryflag_list.get(ii).getImage());
            guessflagimage3.setImageResource(countryflag_list.get(iii).getImage());
            correctFlag = guessflagimage1;
        } else if (iv == 2) {
            guessflagimage1.setImageResource((countryflag_list.get(ii).getImage()));
            guessflagimage2.setImageResource(currentCountry.getImage());
            guessflagimage3.setImageResource(countryflag_list.get(iii).getImage());
            correctFlag = guessflagimage2;
        } else {
            guessflagimage1.setImageResource(countryflag_list.get(iii).getImage());
            guessflagimage2.setImageResource(countryflag_list.get(ii).getImage());
            guessflagimage3.setImageResource(currentCountry.getImage());
            correctFlag = guessflagimage3;
        }

        txt_nameofCountry.setText(currentCountry.getName());
    }

    //validating the click images and show the status of the answer
    void ifCorrect(ImageView img) {
        if (img == correctFlag) {
            txt_statusofAnswer.setTextColor(Color.parseColor("#008000"));
            txt_statusofAnswer.setText("Correct!");
        } else {
            txt_statusofAnswer.setTextColor(Color.parseColor("#FF0000"));
            txt_statusofAnswer.setText("Wrong!");
        }
    }
}
