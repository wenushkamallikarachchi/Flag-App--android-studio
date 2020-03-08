package com.example.cwflag;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

public class GridViewSuggestAdapter extends BaseAdapter {

    Button button;

    private List<String> suggest;
    private Context context;
    private Guesshin guesshin;

    public GridViewSuggestAdapter(List<String> suggest, Context context, Guesshin guesshin) {
        this.suggest = suggest;
        this.context = context;
        this.guesshin = guesshin;
    }


    @Override
    public int getCount() {
        return suggest.size();
    }

    @Override
    public Object getItem(int position) {
        return suggest.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            if (suggest.get(position).equals("null")) {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setBackgroundColor(Color.DKGRAY);
            } else {
                button = new Button(context);
                button.setLayoutParams(new GridView.LayoutParams(85, 85));
                button.setPadding(8, 8, 8, 8);
                button.setBackgroundColor(Color.DKGRAY);
                button.setTextColor(Color.GREEN);
                button.setText(suggest.get(position));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //IF correct answer contains character user selected

                        if (String.valueOf(guesshin.answer).contains(suggest.get(position))) {
                            char answercompare = suggest.get(position).charAt(0);
                            for (int i = 0; i < guesshin.answer.length; i++) {
                                if (answercompare == guesshin.answer[i])
                                    Database.user_submit_answer[i] = answercompare;
                            }
                            //updating the ui

                            GridViewAnswerAdapter answerAdapter = new GridViewAnswerAdapter(Database.user_submit_answer, context);
                            guesshin.gridviewanswer.setAdapter(answerAdapter);
                            answerAdapter.notifyDataSetChanged();

                            // remove from suggest source
                            guesshin.suggestion.set(position, "null");
                            guesshin.suggestAdapter = new GridViewSuggestAdapter(guesshin.suggestion, context, guesshin);
                            guesshin.gridviewquestion.setAdapter(guesshin.suggestAdapter);
                            guesshin.suggestAdapter.notifyDataSetChanged();
                        } else {
                            guesshin.suggestion.set(position, "null");
                            guesshin.suggestAdapter = new GridViewSuggestAdapter(guesshin.suggestion, context, guesshin);
                            guesshin.gridviewquestion.setAdapter(guesshin.suggestAdapter);
                            guesshin.suggestAdapter.notifyDataSetChanged();
                        }


                    }

                });
            }

        } else
            button = (Button) convertView;
        return button;

    }

}