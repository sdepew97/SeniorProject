package com.example.apphomepages.SearchAndSort.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.example.apphomepages.General.DataTypes.Color;

import java.util.ArrayList;
import java.util.List;

public class ArrayQuicksortDrawable extends Drawable implements Animatable
{
    private final Paint mMainPaint;
    private final Paint mSecondPaint;
    private final Paint mFoundPaint;
    private final Paint mTextPaint;

    private ArrayList<Integer> numbers;
    private List<Integer> valuesBeingArranged;
    private Integer pivot;

    //An ArraySearchDrawable constructor for searching
    public ArrayQuicksortDrawable(Color main, Color secondary, Color found, Integer pivot, List<Integer> valuesBeingArranged, ArrayList<Integer> numbers)
    {
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
        this.pivot = pivot;
        this.valuesBeingArranged = valuesBeingArranged;
    }

    @Override
    public void draw(Canvas canvas)
    {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numSquares = numbers.size();
        int widthSideLength = width / numSquares;
        int heightSideLength = height;

        mTextPaint.setTextSize(widthSideLength / 3);

        int left = 0;
        int top = 0;

        Rect[] rectangles = new Rect[numSquares];

        for (int i = 0; i < numSquares; i++)
        {
            rectangles[i] = new Rect(left, top, left + widthSideLength, top + heightSideLength);

            left += widthSideLength;

            if (valuesBeingArranged.size() > 1 && valuesBeingArranged.get(0) <= i && i <= valuesBeingArranged.get(1))
            {
                canvas.drawRect(rectangles[i], mSecondPaint);
            } else
            {
                canvas.drawRect(rectangles[i], mMainPaint);
            }

            if (pivot == i)
            {
                canvas.drawRect(rectangles[i], mFoundPaint);
            }

            canvas.drawText(Integer.toString(numbers.get(i)), rectangles[i].centerX(), rectangles[i].centerY(), mTextPaint);
        }
    }

    @Override
    public void setAlpha(int alpha)
    {
        // This method is required
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter)
    {
        // This method is required
    }

    @Override
    public int getOpacity()
    {
        // Must be PixelFormat.UNKNOWN, TRANSLUCENT, TRANSPARENT, or OPAQUE
        return PixelFormat.OPAQUE;
    }

    @Override
    public void start()
    {

    }

    @Override
    public void stop()
    {

    }

    @Override
    public boolean isRunning()
    {
        return false;
    }
}