package com.example.cwflag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Guesshin extends AppCompatActivity {

    public Button bt_submit;
    public GridView gridviewanswer, gridviewquestion;
    public ImageView guesshinimage;

    Database dbase;
    public char[] answer;
    String correct_answer;

    public GridViewAnswerAdapter answerAdapter;
    public GridViewSuggestAdapter suggestAdapter;
    public List<String> suggestion = new ArrayList<>();

    int[] flags = {
            R.drawable.andorra,
            R.drawable.algeria,
            R.drawable.united_arab_emirates,
            R.drawable.afghanistan,
            R.drawable.antigua_and_barbuda,
            R.drawable.anguilla,
            R.drawable.albania,
            R.drawable.armenia,
            R.drawable.angola,
            R.drawable.antarctica,
            R.drawable.argentina,
            R.drawable.american_samoa,
            R.drawable.austria,
            R.drawable.australia,
            R.drawable.aruba,
            R.drawable.azerbaijan,
            R.drawable.bosnia_and_herzegovina,
            R.drawable.barbados,
            R.drawable.bangladesh,
            R.drawable.belgium,
            R.drawable.burkina_faso,
            R.drawable.bulgaria,
            R.drawable.bahrain,
            R.drawable.burundi,
            R.drawable.benin,
            R.drawable.bermuda,
            R.drawable.brunei_darussalam,
            R.drawable.bermuda,
            R.drawable.bolivia_plurinational_state_of,
            R.drawable.caribbean_netherlands,
            R.drawable.brazil,
            R.drawable.bahamas,
            R.drawable.bouvet_island,
            R.drawable.botswana,
            R.drawable.belarus,
            R.drawable.canada,
            R.drawable.cocos,
            R.drawable.congo,
            R.drawable.central_african_republic,
            R.drawable.congo,
            R.drawable.croatia,
            R.drawable.cook_islands,
            R.drawable.chile,
            R.drawable.cameroon,
            R.drawable.china,
            R.drawable.colombia,
            R.drawable.costa_rica,
            R.drawable.cuba,
            R.drawable.cape_verde,
            R.drawable.cura,
            R.drawable.christmas_island,
            R.drawable.cyprus,
            R.drawable.czech_republic,
            R.drawable.djibouti,
            R.drawable.denmark,
            R.drawable.dominican_republic,
            R.drawable.dominica,
            R.drawable.ecuador,
            R.drawable.estonia,
            R.drawable.egypt,
            R.drawable.ethiopia,
            R.drawable.europe,
            R.drawable.finland,
            R.drawable.fiji,
            R.drawable.falkland_islands,
            R.drawable.faroe_islands,
            R.drawable.gabon,
            R.drawable.england,
            // R.drawable.//
            R.drawable.grenada,
            R.drawable.georgia,
            R.drawable.french_guiana,
            R.drawable.guernsey,
            R.drawable.ghana,
            R.drawable.gambia,
            R.drawable.greenland,
            R.drawable.guyana,
            R.drawable.hong_kong,
            R.drawable.haiti,
            R.drawable.honduras,
            R.drawable.hungary,


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guesshin);

        //init view
        initView();


    }

    private void initView() {
        gridviewanswer = (GridView) findViewById(R.id.gridviewanswer);
        gridviewquestion = (GridView) findViewById(R.id.gridviewquestion);

        guesshinimage = (ImageView) findViewById(R.id.guesshinimage);

        //add setuplist
        setupList();

        bt_submit = (Button) findViewById(R.id.bt_submit);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = "";
                for (int i = 0; i < Database.user_submit_answer.length; i++)
                    result += String.valueOf(Database.user_submit_answer[i]);

                if (result.equals(correct_answer)) {
                    Toast.makeText(getApplicationContext(), "Correct!! This Flag  is " + result, Toast.LENGTH_SHORT).show();

                    //resetting
                    Database.count = 0;
                    Database.user_submit_answer = new char[correct_answer.length()];

                    //Set adapter
                    GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(setupNullList(), getApplicationContext());
                    gridviewanswer.setAdapter(answerAdapter);

                    GridViewSuggestAdapter suggestAdapter = new GridViewSuggestAdapter(suggestion, getApplicationContext(), Guesshin.this);
                    gridviewquestion.setAdapter(suggestAdapter);
                    suggestAdapter.notifyDataSetChanged();


                    setupList();
                } else {
                    Toast.makeText(Guesshin.this, "Incorrect!!", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private void setupList() {
        //random flags to system
        Random r = new Random();
        int imageselected = flags[r.nextInt(flags.length)];
        guesshinimage.setImageResource(imageselected);

        correct_answer = getResources().getResourceName(imageselected);
        correct_answer = correct_answer.substring(correct_answer.lastIndexOf("/") + 1);

        answer = correct_answer.toCharArray();

        Database.user_submit_answer = new char[answer.length];

        //adding alphabet characters to the list
        suggestion.clear();
        for (char item : answer) {
            //add flags to the list
            suggestion.add(String.valueOf(item));

        }

        //random adding some characters to list
        for (int i = answer.length; i < answer.length * 2; i++)
            suggestion.add(Database.alphabet[r.nextInt(Database.alphabet.length)]);


        //sort random
        Collections.shuffle(suggestion);

        //set the grid view

        answerAdapter = new GridViewAnswerAdapter(setupNullList(), this);
        suggestAdapter = new GridViewSuggestAdapter(suggestion, this, this);


        answerAdapter.notifyDataSetChanged();
        suggestAdapter.notifyDataSetChanged();


        gridviewquestion.setAdapter(suggestAdapter);
        gridviewanswer.setAdapter(answerAdapter);


    }


    private char[] setupNullList() {
        char result[] = new char[answer.length];
        for (int i = 0; i < answer.length; i++)
            result[i] = ' ';
        return result;
    }

}