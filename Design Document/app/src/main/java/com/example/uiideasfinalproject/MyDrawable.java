package com.example.uiideasfinalproject;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class MyDrawable extends Drawable {
    private final Paint mRedPaint;
    private final Paint mLinePaint;

    public MyDrawable(int red, int green, int blue) {
        // Set up color and text size
        mRedPaint = new Paint();
        mRedPaint.setARGB(255, red, green, blue);
        mLinePaint = new Paint();
        mLinePaint.setARGB(255, 0, 0, 0);
        mLinePaint.setTextSize(60);
        mLinePaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public void draw(Canvas canvas) {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int sidelength = 100;


        Rect[] rectangles = new Rect[6];
        rectangles[0] = new Rect(height/8, width/8, height/8+sidelength, width/8+sidelength);

        // Draw a red circle in the center
        canvas.drawRect(rectangles[0], mRedPaint);
        canvas.drawLine(height/8, width/8, height/8+sidelength, width/8, mLinePaint);
        canvas.drawText("12", rectangles[0].centerX(), rectangles[0].centerY(), mLinePaint);
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