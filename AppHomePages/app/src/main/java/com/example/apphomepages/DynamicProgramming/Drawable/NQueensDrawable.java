package com.example.apphomepages.DynamicProgramming.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.NQueensReturnType;

import androidx.annotation.Nullable;

public class NQueensDrawable extends Drawable implements Animatable
{
    private final Paint mSquare1Paint;
    private final Paint mSquare2Paint;
    private final Paint mQueenPlaced;
    private final Paint mLookAtPaint;

    private final Paint mTextPaint;
    private final Paint mLinePaint;

    private NQueensReturnType frameToAnimate;

    public NQueensDrawable(Color square1, Color square2, Color queenPlaced, Color lookAt, NQueensReturnType frameToAnimate)
    {
        // Set up color and text size
        mSquare1Paint = new Paint();
        mSquare1Paint.setARGB(255, square1.getRed(), square1.getGreen(), square1.getBlue());

        mSquare2Paint = new Paint();
        mSquare2Paint.setARGB(255, square2.getRed(), square2.getGreen(), square2.getBlue());

        mQueenPlaced = new Paint();
        mQueenPlaced.setARGB(255, queenPlaced.getRed(), queenPlaced.getGreen(), queenPlaced.getBlue());

        mLookAtPaint = new Paint();
        mLookAtPaint.setARGB(255, lookAt.getRed(), lookAt.getGreen(), lookAt.getBlue());

        mTextPaint = new Paint();
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mLinePaint = new Paint();
        mLinePaint.setARGB(255, 0, 0, 0);
        mLinePaint.setTextAlign(Paint.Align.CENTER);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(10);

        this.frameToAnimate = frameToAnimate;
    }

    @Override
    public void draw(Canvas canvas)
    {
        //Extract usable information from the frame
        boolean[][] board = frameToAnimate.getBoard();
        boolean checkingIfSafe = frameToAnimate.isCheckingIfSafe();
        int rowBeingScanned = frameToAnimate.getRowBeingScanned();
        int columnBeingScanned = frameToAnimate.getColumnBeingScanned();


        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numRectangles = board.length;
        int widthOfSquare = height / numRectangles;
        int heightOfSquare = height / numRectangles;
        mTextPaint.setTextSize(widthOfSquare * 3 / 4);

        int left = (width - height) / 2;
        int top = 0;

        //This places the nodes and puts their value in their center (this assumes that the value of the nodes in the graph is unique!)
        for (int i = 0; i < numRectangles; i++)
        {
            for (int j = 0; j < numRectangles; j++)
            {
                //Check if checking if a square is safe to place a queen
                if (checkingIfSafe && i == rowBeingScanned && j == columnBeingScanned)
                {
                    canvas.drawRect(left, top, left + widthOfSquare, top + heightOfSquare, mLookAtPaint);
                } else if (board[i][j])
                {
                    canvas.drawRect(left, top, left + widthOfSquare, top + heightOfSquare, mQueenPlaced);
                } else if (i % 2 == j % 2) //if the coordinates have the same parity, make it color 1 for the square
                {
                    canvas.drawRect(left, top, left + widthOfSquare, top + heightOfSquare, mSquare1Paint);
                } else
                {
                    canvas.drawRect(left, top, left + widthOfSquare, top + heightOfSquare, mSquare2Paint);
                }

                left += widthOfSquare;
            }
            left = (width - height) / 2;
            top += heightOfSquare;
        }
    }


    @Override
    public void setAlpha(int alpha)
    {
        // This method is required
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter)
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
        // This method is required
    }

    @Override
    public void stop()
    {
        // This method is required
    }

    @Override
    public boolean isRunning()
    {
        return false;
    }
}
