package com.example.apphomepages.DynamicProgramming.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.MinEditReturnType;

import androidx.annotation.Nullable;

public class MinEditDrawable extends Drawable implements Animatable
{
    private final Paint mSquarePaint;
    private final Paint mCurrentPaint;
    private final Paint mLookAtPaint;

    private final Paint mTextPaint;
    private final Paint mLinePaint;

    private MinEditReturnType frameToAnimate;
    private String target;
    private String source;
    private boolean start;

    public MinEditDrawable(MinEditReturnType frameToAnimate, String target, String source, boolean start)
    {
        // Set up color and text size
        mSquarePaint = new Paint();
        mSquarePaint.setARGB(255, Color.getMain().getRed(), Color.getMain().getGreen(), Color.getMain().getBlue());

        mCurrentPaint = new Paint();
        mCurrentPaint.setARGB(255, Color.getFound().getRed(), Color.getFound().getGreen(), Color.getFound().getBlue());

        mLookAtPaint = new Paint();
        mLookAtPaint.setARGB(255, Color.getCurrent().getRed(), Color.getCurrent().getGreen(), Color.getCurrent().getBlue());

        mTextPaint = new Paint();
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mLinePaint = new Paint();
        mLinePaint.setARGB(255, 0, 0, 0);
        mLinePaint.setTextAlign(Paint.Align.CENTER);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(10);

        this.frameToAnimate = frameToAnimate;
        this.target = target;
        this.source = source;
        this.start = start;
    }

    @Override
    public void draw(Canvas canvas)
    {
        //Extract usable information from the frame
        int[][] board = frameToAnimate.getBoard();
        boolean minOperation = frameToAnimate.isMinOperation();
        int i = frameToAnimate.getI();
        int j = frameToAnimate.getJ();
        String[] valuesToMin = frameToAnimate.getValuesToMin();

        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numRectanglesHeight = board.length;
        int numRectanglesWidth = board[0].length;
        int widthOfSquare = width / numRectanglesWidth;
        int heightOfSquare = height / numRectanglesHeight;
        mTextPaint.setTextSize((float) (widthOfSquare / 6 * 1.0));

        int left = 0;
        int top = 0;

        Rect[][] rectangles = new Rect[board.length][board[0].length];

        //This places the nodes and puts their value in their center (this assumes that the value of the nodes in the graph is unique!)
        for (int x = 0; x < numRectanglesHeight; x++)
        {
            for (int y = 0; y < numRectanglesWidth; y++)
            {
                //rectangle
                rectangles[x][y] = new Rect(left, top, left + widthOfSquare, top + heightOfSquare);

                if (minOperation)
                {
                    //Draw all the surrounding squares as look at color...
                    if ((x == i - 1 && y == j) || (x == i && y == j - 1) || (x == i - 1 && y == j - 1))
                    {
                        canvas.drawRect(rectangles[x][y], mLookAtPaint);
                    }

                }

                if (x == i && y == j)
                {
                    canvas.drawRect(rectangles[x][y], mCurrentPaint);
                } else
                {
                    canvas.drawRect(rectangles[x][y], mSquarePaint);
                }

                //Vertical line
                canvas.drawLine(left, 0, left, height, mLinePaint);

                left += widthOfSquare;

                if ((x == 0) || (y == 0))
                {
                    //TODO: draw letter of either target or source!
                    if (x == 0 && y == 0)
                    {
                        canvas.drawText("", rectangles[x][y].centerX(), rectangles[x][y].centerY(), mTextPaint);
                    } else if (x == 0)
                    {
                        canvas.drawText(target.charAt(y - 1) + "", rectangles[x][y].centerX(), rectangles[x][y].centerY(), mTextPaint);
                    } else
                    { //y==0
                        canvas.drawText(source.charAt(x - 1) + "", rectangles[x][y].centerX(), rectangles[x][y].centerY(), mTextPaint);
                    }
                } else
                {
                    if (minOperation)
                    {
                        canvas.drawText("min(" + valuesToMin[0] + "," + valuesToMin[1] + "," + valuesToMin[2] + ")", rectangles[x][y].centerX(), rectangles[x][y].centerY(), mTextPaint);
                    } else
                    {
                        canvas.drawText(Integer.toString(board[x][y]), rectangles[x][y].centerX(), rectangles[x][y].centerY(), mTextPaint);
                    }
                }
            }

            left = 0;
            top += heightOfSquare;

            //Horizontal line
            canvas.drawLine(left, top, width, top, mLinePaint);
        }

        //Final horizontal and vertical lines
        canvas.drawLine(0, 0, width, 0, mLinePaint);
        canvas.drawLine(width, 0, width, height, mLinePaint);
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
