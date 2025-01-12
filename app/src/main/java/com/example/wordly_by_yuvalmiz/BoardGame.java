package com.example.wordly_by_yuvalmiz;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;

public class BoardGame extends View {
    private static final int ROWS = 6;  // 6 rows in the grid
    private static final int COLS = 5;  // 5 columns in the grid
    private static final float PADDING = 20f;  // Padding around the grid

    private Paint gridPaint;

    public BoardGame(Context context) {
        super(context);
        init();
    }

    public BoardGame(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BoardGame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // Initialize the paint object for drawing the grid lines
    private void init() {
        gridPaint = new Paint();
        gridPaint.setColor(0xFF000000);  // Set grid color (black)
        gridPaint.setStyle(Paint.Style.STROKE);  // Only draw the border (not filled)
        gridPaint.setStrokeWidth(4);  // Line width for grid borders
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Get the width and height of the view
        int width = getWidth();
        int height = getHeight();

        // Calculate the width and height for each cell in the grid
        float cellWidth = (width - 2 * PADDING) / COLS;
        float cellHeight = (height - 2 * PADDING) / ROWS;

        // Draw the grid
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                // Calculate the position of each cell
                float left = PADDING + col * cellWidth;
                float top = PADDING + row * cellHeight;
                float right = left + cellWidth;
                float bottom = top + cellHeight;

                // Draw the rectangle for each grid cell
                canvas.drawRect(left, top, right, bottom, gridPaint);
            }
        }
    }
}
