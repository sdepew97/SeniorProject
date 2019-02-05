package com.example.apphomepages;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class ArraySortDrawable extends Drawable {
    private final Paint mPaint;
    private final Paint mTextPaint;

    private ArrayList<Integer> numbers;

    //An ArraySearchDrawable constructor for searching
    public ArraySortDrawable(Color main, ArrayList<Integer> numbers) {
        // Set up color and text size
        mPaint = new Paint();
        mPaint.setARGB(255, main.getRed(), main.getGreen(), main.getBlue());

        mTextPaint = new Paint();
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setTextSize(60);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        this.numbers = numbers;
    }

    @Override
    public void draw(Canvas canvas) {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numSquares = numbers.size();
        int sidelength = width / numSquares;

        int left = 0;
        int top = width / 6;

        Rect[] rectangles = new Rect[numSquares];

        for (int i = 0; i < numSquares; i++) {
            rectangles[i] = new Rect(left, top, left + sidelength, top + sidelength);
            left += sidelength;

            canvas.drawRect(rectangles[i], mPaint);
            canvas.drawText(Integer.toString(numbers.get(i)), rectangles[i].centerX(), rectangles[i].centerY(), mTextPaint);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        // This method is required
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        // This method is required
    }

    @Override
    public int getOpacity() {
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        return PixelFormat.OPAQUE;
    }
}