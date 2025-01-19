package com.example.wordly_by_yuvalmiz;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Cell {
    int id;  // Unique ID for each cell
    float left, top, right, bottom;  // Coordinates of the cell
    private int backgroundColor;  // Background color of the cell

    // Constructor for creating a cell
    public Cell(int id, float left, float top, float right, float bottom) {
        this.id = id;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
          // Default color is white
    }

    // Method to set the background color of the cell
    public void setBackgroundColor(int color) {
        this.backgroundColor = color;
    }
    public int getBackgroundColor() {
        return this.backgroundColor;
    }

    // Method to draw the cell on the canvas
    public void draw(Canvas canvas, Paint paint) {
        // Set the background color
        paint.setColor(backgroundColor);
        canvas.drawRect(left, top, right, bottom, paint);

        // Draw the border of the cell (if needed)
        paint.setColor(Color.BLACK);  // Black border color
        paint.setStyle(Paint.Style.STROKE);  // Only draw the border (not filled)
        paint.setStrokeWidth(4);  // Line width for grid borders
        canvas.drawRect(left, top, right, bottom, paint);
    }
}