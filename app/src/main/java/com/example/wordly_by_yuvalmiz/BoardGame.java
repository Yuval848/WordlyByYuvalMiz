package com.example.wordly_by_yuvalmiz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class BoardGame extends View {
    private CellBox cellBox;//תאים שאליהם האותיות יכנסו
    private CellBox [][] cellBoxes;
    private Paint p;
    private boolean isFirstTime = true;


    public BoardGame(Context context) {
        super(context);

        cellBoxes = new CellBox[5][6];
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        if(isFirstTime)
        {
            initBoard(canvas);
            isFirstTime = false;
        }

        drawBoard(canvas);

    }





    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                cellBoxes[i][j].draw(canvas);
            }
        }
    }

    private void initBoard(Canvas canvas) {
        int x = 0;
        int y = 0;
        int w = canvas.getWidth()/5;
        int h = w;
        int color = Color.BLACK;

        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 6; j++)
            {

                cellBoxes[i][j] = new CellBox(x,y,w,h,color);
                x = x+w;
            }
            x = 0;
            y = y + h;
        }
    }
}
