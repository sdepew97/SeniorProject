package com.example.apphomepages;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

public class ArraySortDrawable extends Drawable implements Animatable {
    private final Paint mMainPaint;
    private final Paint mSecondPaint;
    private final Paint mFoundPaint;
    private final Paint mTextPaint;

    private ArrayList<Integer> numbers;
    private int squareToHighlight;
    private int currentSquare;

    //An ArraySearchDrawable constructor for searching
    public ArraySortDrawable(Color main, Color secondary, Color found, int squareToHighlight, int currentSquare, ArrayList<Integer> numbers) {
        // Set up color and text size
        mMainPaint = new Paint();
        mMainPaint.setARGB(255, main.getRed(), main.getGreen(), main.getBlue());

        mSecondPaint = new Paint();
        mSecondPaint.setARGB(255, secondary.getRed(), secondary.getGreen(), secondary.getBlue());

        mTextPaint = new Paint();
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setTextSize(60);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mFoundPaint = new Paint();
        mFoundPaint.setARGB(255, found.getRed(), found.getGreen(), found.getBlue());


        this.numbers = numbers;
        this.squareToHighlight = squareToHighlight;
        this.currentSquare = currentSquare;
    }

    @Override
    public void draw(Canvas canvas) {
        // Get the drawable's bounds
        int width = getBounds().width();
        int numSquares = numbers.size();
        int sidelength = width / numSquares;

        int left = 0;
        int top = width / 6;

        Rect[] rectangles = new Rect[numSquares];

        for (int i = 0; i < numSquares; i++) {
            rectangles[i] = new Rect(left, top, left + sidelength, top + sidelength);

            left += sidelength;

            if(squareToHighlight == i) {
                canvas.drawRect(rectangles[i], mFoundPaint);
            } else if (currentSquare > i) {
                canvas.drawRect(rectangles[i], mSecondPaint);
            } else {
                canvas.drawRect(rectangles[i], mMainPaint);
            }

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

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}