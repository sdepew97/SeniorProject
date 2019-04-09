package com.example.apphomepages.SearchAndSort.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.QuickSortReturnType;

import java.util.ArrayList;

public class ArrayQuicksortDrawable extends Drawable implements Animatable
{
    private final Paint mMainPaint;
    private final Paint mSecondPaint;
    private final Paint mPivotPaint;
    private final Paint mCurrentPaint;
    private final Paint mTextPaint;

    private QuickSortReturnType t;
    private boolean start; //flag to tell if it is the start
    private boolean end; //flag to tell if it is the end

    //An ArraySearchDrawable constructor for searching
    public ArrayQuicksortDrawable(boolean start, boolean end, QuickSortReturnType t)
    {
        Color main = Color.getMain();
        Color secondary = Color.getSecondary();
        Color found = Color.getFound();
        Color current = Color.getCurrent();

        // Set up color and text size
        mMainPaint = new Paint();
        mMainPaint.setARGB(255, main.getRed(), main.getGreen(), main.getBlue());

        mSecondPaint = new Paint();
        mSecondPaint.setARGB(255, secondary.getRed(), secondary.getGreen(), secondary.getBlue());

        mPivotPaint = new Paint();
        mPivotPaint.setARGB(255, found.getRed(), found.getGreen(), found.getBlue());

        mCurrentPaint = new Paint();
        mCurrentPaint.setARGB(255, current.getRed(), current.getGreen(), current.getBlue());

        mTextPaint = new Paint();
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setTextSize(60);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        this.t = t;
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(Canvas canvas)
    {
        ArrayList<Integer> numbers = t.getList();
        Integer a = t.getA();
        Integer b = t.getB();
        Integer pivot = t.getPivot();
        Integer beingViewed = t.getBeingViewed();
        boolean partitioning = t.isPartitioning();

        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numSquares = numbers.size();
        int widthSideLength = width / numSquares;
        int heightSideLength = height / numSquares;

        mTextPaint.setTextSize((float) (widthSideLength / 3.0));

        //Center the array
        int left = 0;
        int top = (height - heightSideLength) / 2;

        Rect[] rectangles = new Rect[numSquares];

        for (int i = 0; i < numSquares; i++)
        {
            rectangles[i] = new Rect(left, top, left + widthSideLength, top + heightSideLength);

            left += widthSideLength;

            if (start || end)
            {
                if (start)
                {
                    canvas.drawRect(rectangles[i], mMainPaint);
                } else
                {
                    canvas.drawRect(rectangles[i], mSecondPaint);
                }
            } else
            {
                //highlight the area of the array being examined
                if (a <= i && i <= b)
                {
                    canvas.drawRect(rectangles[i], mSecondPaint);
                } else
                {
                    canvas.drawRect(rectangles[i], mMainPaint);
                }

                if (pivot == i)
                {
                    canvas.drawRect(rectangles[i], mPivotPaint);
                }

                if (partitioning && beingViewed == i)
                {
                    canvas.drawRect(rectangles[i], mCurrentPaint);
                }
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