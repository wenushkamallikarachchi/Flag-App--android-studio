package com.example.cwflag;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Advanced_lvl extends AppCompatActivity {

//identifying the ids of the Imageview,TextView,EditText
    ImageView advancedimage1,advancedimage2,advancedimage3;
    EditText editText,editText2,editText3;
    TextView txt_advancedCorrectAnswer1,txt_advancedCorrectAnswer2,txt_advancedCorrectAnswer3,txt_advancedstatus1,txt_advancedstatus2,txt_advancedstatus3;
    Button button1;


    Random rand1,rand2,rand3;
    Database dbase;
    private int flag1,flag2,flag3;

    //Create an arraylist object
    ArrayList<CountryItem> countryflag_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_lvl);

        rand1 = new Random();
        rand2 = new Random();
        rand3 = new Random();
        dbase = new Database();

        countryflag_list = new ArrayList();


        button1 =findViewById(R.id.button1);
        advancedimage1=findViewById(R.id.advancedimage1);
        advancedimage2=findViewById(R.id.advancedimage2);
        advancedimage3=findViewById(R.id.advancedimage3);
        txt_advancedCorrectAnswer1=findViewById(R.id.txt_advancedCorrectAnswer1);
        txt_advancedCorrectAnswer2=findViewById(R.id.txt_advancedCorrectAnswer2);
        txt_advancedCorrectAnswer3=findViewById(R.id.txt_advancedCorrectAnswer3);
        txt_advancedstatus1=findViewById(R.id.txt_advacedstatus1);
        txt_advancedstatus2=findViewById(R.id.txt_advacedstatus2);
        txt_advancedstatus3=findViewById(R.id.txt_advacedstatus3);
        editText=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);

        //getting the random flags to ImageViews
        rand1 = new Random();
        flag1 = rand1.nextInt(dbase.answers.length);
        advancedimage1.setImageResource(dbase.flags[flag1]);

        rand2 = new Random();
        flag2 = rand2.nextInt(dbase.answers.length);
        advancedimage2.setImageResource(dbase.flags[flag2]);

        rand3 = new Random();
        flag3 = rand3.nextInt(dbase.answers.length);
        advancedimage3.setImageResource(dbase.flags[flag3]);

        //when the click the button
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button1.getText().toString().toLowerCase().equals("submit")){
                    Log.d("Submit_button","Submit button  is working");

                    txt_advancedstatus1.setVisibility(View.VISIBLE);
                    txt_advancedstatus2.setVisibility(View.VISIBLE);
                    txt_advancedstatus3.setVisibility(View.VISIBLE);

                    //correcting the answer user given
                    if (editText.getText().toString().equals(dbase.answers[flag1])){
                        Log.d("Flag1","First flag is working");
                        txt_advancedstatus1.setTextColor(Color.GREEN);
                        txt_advancedstatus1.setText("Correct!!");


                    }else{
                        //if answer is wrong display the error message and the correct answer
                        txt_advancedstatus1.setTextColor(Color.RED);
                        txt_advancedstatus1.setText("Incorrect!!");
                        txt_advancedCorrectAnswer1.setText(dbase.answers[flag1]);
                    }

                    if (editText2.getText().toString().equals(dbase.answers[flag2])){
                        Log.d("Flag2","second flag is working");
                        txt_advancedstatus2.setTextColor(Color.GREEN);
                        txt_advancedstatus2.setText("Correct!!");

                    }else {
                        txt_advancedstatus2.setText("Incorrect");
                        txt_advancedstatus2.setTextColor(Color.RED);
                        txt_advancedCorrectAnswer2.setText(dbase.answers[flag2]);

                    }
                    if (editText3.getText().toString().equals(dbase.answers[flag3])){
                        Log.d("Flag3","Third flag is working");
                        txt_advancedstatus3.setTextColor(Color.GREEN);
                        txt_advancedstatus3.setText("Correct!!");

                    }else {
                        //if answer is wrong display the error message and the correct answer
                        txt_advancedstatus3.setText("Incorrect");
                        txt_advancedstatus3.setTextColor(Color.RED);
                        txt_advancedCorrectAnswer3.setText(dbase.answers[flag3]);
                    }
                    //finish the typing answer and submit the answers and show the next button
                    button1.setText("next");


                }else {
                    Log.d("Submit_button","Submit button is not working");
                    Intent intent = new Intent(getApplicationContext(), Advanced_lvl.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
}
