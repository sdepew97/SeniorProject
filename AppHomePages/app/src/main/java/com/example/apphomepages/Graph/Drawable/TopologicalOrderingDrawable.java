package com.example.apphomepages.Graph.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.DataTypes.Point;
import com.example.apphomepages.Graph.Algorithms.GraphAlgorithms;
import com.example.apphomepages.Graph.HelperMethods.GraphHelperMethods;

import java.util.ArrayList;

public class TopologicalOrderingDrawable extends Drawable implements Animatable
{
    private final Paint mMainPaint;
    private final Paint mSecondPaint;
    private final Paint mFoundPaint;
    private final Paint mTextPaint;
    private final Paint mLinePaint;

    private Graph<String> graph;
    private String nodeToHighlight;
    private boolean startOrEnd;

    //An ArraySearchDrawable constructor for searching
    public TopologicalOrderingDrawable(Color main, Color secondary, Color found, String nodeToHighlight, Graph<String> graph, boolean startOrEnd)
    {
        // Set up color and text size
        mMainPaint = new Paint();
        mMainPaint.setARGB(255, main.getRed(), main.getGreen(), main.getBlue());

        mSecondPaint = new Paint();
        mSecondPaint.setARGB(255, secondary.getRed(), secondary.getGreen(), secondary.getBlue());

        mTextPaint = new Paint();
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mLinePaint = new Paint();
        mLinePaint.setARGB(255, 0, 0, 0);
        mLinePaint.setTextAlign(Paint.Align.CENTER);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(10);

        mFoundPaint = new Paint();
        mFoundPaint.setARGB(255, found.getRed(), found.getGreen(), found.getBlue());

        this.graph = graph;
        this.nodeToHighlight = nodeToHighlight;
        this.startOrEnd = startOrEnd;
    }

    @Override
    public void draw(Canvas canvas)
    {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numCircles = graph.numNodes();
        int radius = (int) (height / (GraphHelperMethods.numLayers(graph) + 4.0)); //the radius of the circle, since this means they will never overlap horizontally
        mTextPaint.setTextSize((float) (radius / 2.6));

        int left = 0;
        int top = 0;

        //Gets the center values of all the nodes and the correct value at the nodes in question
        Point[] centersOfCircles = GraphHelperMethods.placeNodes(graph, width, height);
        ArrayList<String> layoutOrder = GraphAlgorithms.breadthFirstSearch(graph, "", true); //this will do a breadth-first traversal, which is also how we are planning to lay out the nodes

        //Draw the lines connecting the nodes
        //TODO (Sarah): draw the connecting edges on the graph
        ArrayList<Node<String>> nodes = graph.getGraphElements();

        for (Node<String> n : nodes)
        {
            ArrayList<Node<String>> adjacentNodes = n.getAdjacentNodes();

            for (Node<String> a : adjacentNodes)
            {
                //TODO: test that the node's value is actually in the list and that this code doesn't error
                Point start = centersOfCircles[layoutOrder.indexOf(n.getNodeValue())];
                Point end = centersOfCircles[layoutOrder.indexOf(a.getNodeValue())];

                canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), mLinePaint);

                //TODO: draw arrowheads here! (new idea...use a bitmap?) https://stackoverflow.com/questions/11975636/how-to-draw-an-arrow-using-android-graphic-class
                //canvas.drawLine((float) ((start.getX() + end.getX()) / 2.0), ((float) ((start.getY() + end.getY()) / 2.0)), (float) ((start.getX() + end.getX()) / 2.0) - 24, ((float) ((start.getY() + end.getY()) / 2.0) - 24), mLinePaint);
                //canvas.drawLine((float) ((start.getX() + end.getX()) / 2.0) - 24, ((float) ((start.getY() + end.getY()) / 2.0) - 24), (float) ((start.getX() + end.getX()) / 2.0) + 24, ((float) ((start.getY() + end.getY()) / 2.0) - 24), mLinePaint);

                //canvas.drawLine((float) (end.getX() - (radius / Math.sqrt(2.0))), (float) (end.getY() + (radius / Math.sqrt(2.0))), (float) (end.getX() - (radius / Math.sqrt(2.0) - radius / 3.0)), (float) (end.getY() + (radius / Math.sqrt(2.0) + radius / 3.0)), mLinePaint);
                //canvas.drawLine((float) (end.getX() - (radius / Math.sqrt(2.0))), (float) (end.getY() + (radius / Math.sqrt(2.0))), (float) (end.getX() - (radius / Math.sqrt(2.0) - radius / 3.0)), (float) (end.getY() + (radius / Math.sqrt(2.0) - radius / 3.0)), mLinePaint);

            }
        }

        //This places the nodes and puts their value in their center (this assumes that the value of the nodes in the graph is unique!)
        for (int i = 0; i < numCircles; i++)
        {
            if (nodeToHighlight.equals(layoutOrder.get(i)) && !startOrEnd)
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mSecondPaint);
            } else
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mMainPaint);
            }

            canvas.drawText(layoutOrder.get(i), centersOfCircles[i].getX(), centersOfCircles[i].getY(), mTextPaint);
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

    private void fillArrow(Paint paint, Canvas canvas, float x0, float y0, float x1, float y1)
    {
        paint.setStyle(Paint.Style.STROKE);

        int arrowHeadLenght = 10;
        int arrowHeadAngle = 45;
        float[] linePts = new float[]{x1 - arrowHeadLenght, y1, x1, y1};
        float[] linePts2 = new float[]{x1, y1, x1, y1 + arrowHeadLenght};
        Matrix rotateMat = new Matrix();

        //get the center of the line
        float centerX = x1;
        float centerY = y1;

        //set the angle
        double angle = Math.atan2(y1 - y0, x1 - x0) * 180 / Math.PI + arrowHeadAngle;

        //rotate the matrix around the center
        rotateMat.setRotate((float) angle, centerX, centerY);
        rotateMat.mapPoints(linePts);
        rotateMat.mapPoints(linePts2);

        canvas.drawLine(linePts[0], linePts[1], linePts[2], linePts[3], paint);
        canvas.drawLine(linePts2[0], linePts2[1], linePts2[2], linePts2[3], paint);
    }
}