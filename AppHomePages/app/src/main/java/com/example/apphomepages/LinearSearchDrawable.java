package com.example.apphomepages;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class LinearSearchDrawable extends Drawable {
    private final Paint mMainPaint;
    private final Paint mSecondPaint;
    private final Paint mFoundPaint;
    private final Paint mTextPaint;

    private int[] numbers;
    private int squareToHighlight;

    private boolean target;

    public LinearSearchDrawable(Color main, Color secondary, Color found, int squareToHighlight, boolean target, int[] numbers) {
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
        this.target = target;
    }

    @Override
    public void draw(Canvas canvas) {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numSquares = numbers.length;
        int sidelength = width / numSquares;

        int left = 0;
        int top = width / 6;

        Rect[] rectangles = new Rect[numSquares];

        for (int i = 0; i < numSquares; i++) {
            rectangles[i] = new Rect(left, top, left + sidelength, top + sidelength);

            left += sidelength;

            if(squareToHighlight == i && !target) {
                canvas.drawRect(rectangles[i], mSecondPaint);
            } else if (squareToHighlight == i && target) {
                canvas.drawRect(rectangles[i], mFoundPaint);
            } else {
                canvas.drawRect(rectangles[i], mMainPaint);
            }

            canvas.drawText(Integer.toString(numbers[i]), rectangles[i].centerX(), rectangles[i].centerY(), mTextPaint);
        }

        // Draw a red circle in the center
        //canvas.drawRect(rectangles[0], mRedPaint);
        //canvas.drawLine(height/8, width/8, height/8+sidelength, width/8, mLinePaint);

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