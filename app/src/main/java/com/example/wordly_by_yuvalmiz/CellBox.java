package com.example.wordly_by_yuvalmiz;

import android.graphics.Canvas;
import android.graphics.Paint;

public class CellBox extends Shape{
    private int w,h;  // w = width h = height
    private Paint p;

    public CellBox(int x, int y,int w, int h, int color) {
        super(x, y, color);
        this.w = w;
        this.h = h;

        p = new Paint();
        p.setColor(color);
        p.setStyle(Paint.Style.FILL);
    }

    public void draw(Canvas canvas)
    {
        canvas.drawRect(x,y,x+w,y+h,p);
    }
}