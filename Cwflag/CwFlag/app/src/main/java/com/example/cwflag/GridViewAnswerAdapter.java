package com.example.cwflag;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

public class GridViewAnswerAdapter extends BaseAdapter {

    private char [] answers;
    private Context context;

    public GridViewAnswerAdapter(char[] answers, Context context) {
        this.answers = answers;
        this.context = context;
    }

    @Override
    public int getCount() {
        return answers.length;
    }

    @Override
    public Object getItem(int position) {
        return answers[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Button button;
        if (convertView == null){
            //creating a new button
            button = new Button(context);
            button.setLayoutParams(new GridView.LayoutParams(85,85));
            button.setPadding(8,8,8,8);
            button.setBackgroundColor(Color.DKGRAY);
            button.setTextColor(Color.GREEN);
            button.setText(String.valueOf(answers[position]));
        }
        else
            button=(Button) convertView;
        return button;
    }
}