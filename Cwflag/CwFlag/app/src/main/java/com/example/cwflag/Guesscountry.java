package com.example.cwflag;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Guesscountry extends AppCompatActivity {

    Random r;
    Database dbase;

    Button button;
    CountryItem currentCountry;
    Spinner spinner;
    ImageView imageView;
    TextView tv_correctName, tv_answerStatus;

    ArrayList<CountryItem> countryflag_list;
    List<String> answerflag_list;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guesscountry);

        r = new Random();
        dbase = new Database();


        //define the ids of the tools
        spinner = findViewById(R.id.spinner1);
        button = findViewById(R.id.bt_guessCsubmit);
        imageView = findViewById(R.id.guesscoun_imageview);
        tv_correctName = findViewById(R.id.bt_answerguess_correct);
        tv_answerStatus = findViewById(R.id.bt_answerguess_country);


        //add a array to a new arraylist
        countryflag_list = new ArrayList();
        answerflag_list = new ArrayList();


        for(int i = 0; i < dbase.answers.length; i++){
            CountryItem m = new CountryItem(dbase.answers[i], dbase.flags[i]);
            countryflag_list.add(m);
            answerflag_list.add(m.getName());
        }

        //shuffle the countryflay arraylist and the sorting the answerflag arraylist
        Collections.sort(answerflag_list);
        Collections.shuffle(countryflag_list);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, answerflag_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //loading the new countryflag
        loadNewCountry();

        spinner.setAdapter(adapter);

        //button onclick method
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (button.getText().equals("Submit")){
                    String selectedCountry = spinner.getSelectedItem().toString();

                    //validating the answer user entered
                    if (currentCountry.getName().equals(selectedCountry)){
                        tv_answerStatus.setTextColor(Color.parseColor("#008000"));
                        tv_answerStatus.setText("Correct!");
                        button.setText("Next");
                    }else {
                        tv_answerStatus.setTextColor(Color.parseColor("#FF0000"));
                        tv_answerStatus.setText("Wrong!");
                        tv_correctName.setText(currentCountry.getName());
                        button.setText("Next");
                    }

                }else{
                    loadNewCountry();
                }

            }
        });

    }

    //loading the new country method
    void loadNewCountry(){
        int i = r.nextInt(countryflag_list.size());
        currentCountry = countryflag_list.get(i);
        //image loading
        imageView.setImageResource(currentCountry.getImage());
        tv_answerStatus.setText("");
        tv_correctName.setText("");
        button.setText("Submit");
        //Toast.makeText(GuessTheCountry.this, currentCountry.getName(), Toast.LENGTH_LONG).show();
    }
}
