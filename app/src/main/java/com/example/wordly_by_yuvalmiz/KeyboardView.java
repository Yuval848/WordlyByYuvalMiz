package com.example.wordly_by_yuvalmiz;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

class KeyboardView extends LinearLayout implements View.OnClickListener {

    String str="";
    Context context;
    TextView tv;

    public  KeyboardView(Context context)
    {
        super(context);
        this.context=context;
        tv=new TextView(context);
        tv.setBackgroundColor(Color.BLACK);
        tv.setTextColor(Color.WHITE);
        tv.setTextDirection(View.TEXT_DIRECTION_RTL);
        tv.setTextSize(30);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setOrientation(VERTICAL);
        this.addView(tv);
        loadLeboard('א');
        loadLeboard('י');
        loadLeboard('ע');
        loadSpaceAndDelete();
    }



    public void loadLeboard(char ch)
    {
        LinearLayout l = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 100, 0, 0);
        l.setLayoutParams(layoutParams);

        for(int i=0;i<9;i++)
        {
            Button btn=new Button(context);
            btn.setBackgroundResource(android.R.drawable.editbox_background);
            btn.setText(String.valueOf(ch));
            //LayoutParams btnParam=new LayoutParams(120,120);
            LayoutParams btnParam=new LayoutParams(85,85);
            btn.setLayoutParams(btnParam);
            l.addView(btn);
            ch++;
            btn.setOnClickListener(this);
        }

        this.addView(l);
    }

    public void loadSpaceAndDelete()
    {
        Button space=new Button(context);
        space.setBackgroundResource(android.R.drawable.editbox_background);
        space.setText(String.valueOf(""));
        space.setBackgroundColor(Color.GRAY);
        LayoutParams btnParam=new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnParam.setMargins(20, 50, 20, 0);
        space.setLayoutParams(btnParam);
        space.setText(String.valueOf("רווח"));
        this.addView(space);

        space.setOnClickListener(this);
        Button delete=new Button(context);
        delete.setBackgroundResource(android.R.drawable.editbox_background);
        delete.setText(String.valueOf("delete"));
        delete.setLayoutParams(btnParam);
        delete.setBackgroundColor(Color.GRAY);

        delete.setOnClickListener(this);

        this.addView(delete);
    }

    @Override

    public void onClick(View v) {
        Button btn = (Button) v;
        String s= btn.getText().toString();
        if(s.equals("delete"))
        {
            if(this.str.length()>0)
                this.str=this.str.substring(0,this.str.length()-1);
        }
        else if(s.equals("רווח"))
        {
            this.str+=" ";
        }
        else
        {
            this.str += btn.getText().toString();
        }
        tv.setText(str);
    }
}

