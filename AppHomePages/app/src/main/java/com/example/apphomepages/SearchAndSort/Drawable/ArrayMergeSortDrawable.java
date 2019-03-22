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

public class ArrayMergeSortDrawable extends Drawable implements Animatable
{
    private final Paint mMainPaint;
    private final Paint mSecondPaint;
    private final Paint mTextPaint;

    private ArrayList<ArrayList<Integer>> arraysToDraw;
    private boolean done;

    //An ArraySearchDrawable constructor for searching
    public ArrayMergeSortDrawable(Color main, Color secondary, ArrayList<ArrayList<Integer>> arraysToDraw, boolean done)
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

        this.arraysToDraw = arraysToDraw;
        this.done = done;
    }

    @Override
    public void draw(Canvas canvas)
    {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numSquares = numSquares(arraysToDraw);
        int widthSideLength = width / (numSquares);
        int heightSideLength = height;

        mTextPaint.setTextSize(widthSideLength / 3);

        int left = 0;
        int top = 0;

        Rect[] rectangles = new Rect[numSquares];

        for (int i = 0; i < arraysToDraw.size(); i++)
        {
            ArrayList<Integer> list = arraysToDraw.get(i);
            for (int j = 0; j < list.size(); j++)
            {
                rectangles[j] = new Rect(left, top, left + widthSideLength, top + heightSideLength);

                left += widthSideLength;

                if (!done)
                    canvas.drawRect(rectangles[j], mMainPaint);
                else
                    canvas.drawRect(rectangles[j], mSecondPaint);

                canvas.drawText(Integer.toString(list.get(j)), rectangles[j].centerX(), rectangles[j].centerY(), mTextPaint);
            }

            if (arraysToDraw.size() > 1)
            {
                left += widthSideLength;
            }
        }
    }

    private int numSquares(ArrayList<ArrayList<Integer>> squareToCount)
    {
        int i = 0;

        for (ArrayList<Integer> list : squareToCount)
        {
            for (Integer item : list)
            {
                i++;
            }
        }

        return i;
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