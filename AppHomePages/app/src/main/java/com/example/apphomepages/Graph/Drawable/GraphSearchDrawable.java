package com.example.apphomepages.Graph.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.Graph;

import java.util.ArrayList;

public class GraphSearchDrawable extends Drawable implements Animatable
{
    private final Paint mMainPaint;
    private final Paint mSecondPaint;
    private final Paint mFoundPaint;
    private final Paint mTextPaint;

    private Graph graph;
    private int nodeToHightlight = -1;

    private boolean target;

    //An ArraySearchDrawable constructor for searching
    public GraphSearchDrawable(Color main, Color secondary, Color found, int nodeToHightlight, boolean target, Graph graph)
    {
        // Set up color and text size
        mMainPaint = new Paint();
        mMainPaint.setARGB(255, main.getRed(), main.getGreen(), main.getBlue());

        mSecondPaint = new Paint();
        mSecondPaint.setARGB(255, secondary.getRed(), secondary.getGreen(), secondary.getBlue());

        mTextPaint = new Paint();
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mFoundPaint = new Paint();
        mFoundPaint.setARGB(255, found.getRed(), found.getGreen(), found.getBlue());


        this.graph = graph;
        this.nodeToHightlight = nodeToHightlight;
        this.target = target;
    }

    @Override
    public void draw(Canvas canvas)
    {

        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        /*
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

            if (squareToHighlight == i && !target)
            {
                canvas.drawRect(rectangles[i], mSecondPaint);
            } else if (squareToHighlight == i && target)
            {
                canvas.drawRect(rectangles[i], mFoundPaint);
            } else
            {
                canvas.drawRect(rectangles[i], mMainPaint);
            }

            canvas.drawText(Integer.toString(numbers.get(i)), rectangles[i].centerX(), rectangles[i].centerY(), mTextPaint);
        }
        */
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