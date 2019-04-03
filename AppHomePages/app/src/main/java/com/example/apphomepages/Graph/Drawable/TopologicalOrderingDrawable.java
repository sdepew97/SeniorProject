package com.example.apphomepages.Graph.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
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
        mLinePaint.setStrokeWidth(4);

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
        int numCircles = graph.getGraphElements().size();
        int radius = (int) (height / (GraphHelperMethods.numLayers(graph) + 4.0)); //the radius of the circle, since this means they will never overlap horizontally
        mTextPaint.setTextSize((float) (radius / 2.6));

        int left = 0;
        int top = 0;

        //Gets the center values of all the nodes and the correct value at the nodes in question
        Point[] centersOfCircles = GraphHelperMethods.placeNodes(graph, width, height);
        ArrayList<Node<String>> layoutOrder = GraphAlgorithms.breadthFirstSearch(graph, null, true); //this will do a breadth-first traversal, which is also how we are planning to lay out the nodes

        //Draw the lines connecting the nodes
        ArrayList<Node<String>> nodes = graph.getGraphElements();

        for (Node<String> n : nodes)
        {
            ArrayList<Node<String>> adjacentNodes = n.getAdjacentNodes();

            for (Node<String> a : adjacentNodes)
            {
                //TODO: test that the node's value is actually in the list and that this code doesn't error
                Point start = centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, n.getNodeId())];
                Point end = centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, a.getNodeId())];

                canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), mLinePaint);
                Matrix m = new Matrix();
                double radians = Math.atan((end.getY() - end.getX() * 1.0) / (start.getY() - start.getX()));
                m.setRotate((float) Math.toDegrees(radians), ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                drawTriangle(canvas, mFoundPaint, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)), 15, m);
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

            canvas.drawText(layoutOrder.get(i).getNodeValue(), centersOfCircles[i].getX(), centersOfCircles[i].getY(), mTextPaint);
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

    //Found code on https://kylewbanks.com/blog/drawing-triangles-rhombuses-and-other-shapes-on-android-canvas and http://android-er.blogspot.com/2014/06/rotate-path-with-matrix.html
    public void drawTriangle(Canvas canvas, Paint paint, int x, int y, int width, Matrix m)
    {
        int halfWidth = width / 2;

        Path path = new Path();
        path.moveTo(x, y - halfWidth); // Top
        path.lineTo(x - halfWidth, y + halfWidth); // Bottom left
        path.lineTo(x + halfWidth, y + halfWidth); // Bottom right
        path.lineTo(x, y - halfWidth); // Back to Top
        path.close();
        path.transform(m); //rotate the triangle as needed

        canvas.drawPath(path, paint);
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