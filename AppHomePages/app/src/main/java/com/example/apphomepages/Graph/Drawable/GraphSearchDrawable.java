package com.example.apphomepages.Graph.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
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

public class GraphSearchDrawable extends Drawable implements Animatable
{
    private final Paint mMainPaint;
    private final Paint mSecondPaint;
    private final Paint mFoundPaint;
    private final Paint mTextPaint;
    private final Paint mLinePaint;

    private Graph<Integer> graph;
    private Node<Integer> nodeToHighlight;

    private boolean target;

    //An ArraySearchDrawable constructor for searching
    public GraphSearchDrawable(Node<Integer> nodeToHighlight, boolean target, Graph<Integer> graph)
    {
        Color main = Color.getMain();
        Color secondary = Color.getSecondary();
        Color found = Color.getFound();

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
        this.target = target;
    }

    @Override
    public void draw(Canvas canvas)
    {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numCircles = graph.getGraphElements().size();
        int radius = height / (GraphHelperMethods.numLayers(graph) * 2 + 1); //the radius of the circle, since this means they will never overlap horizontally
        mTextPaint.setTextSize(radius * 3 / 4);

        int left = 0;
        int top = 0;

        //Gets the center values of all the nodes and the correct value at the nodes in question
        Point[] centersOfCircles = GraphHelperMethods.placeNodes(graph, width, height);
        ArrayList<Node<Integer>> layoutOrder = GraphAlgorithms.breadthFirstSearch(graph, null, true); //this will do a breadth-first traversal, which is also how we are planning to lay out the nodes

        //Draw the lines connecting the nodes
        ArrayList<Node<Integer>> nodes = graph.getGraphElements();

        for (Node<Integer> n : nodes)
        {
            ArrayList<Node<Integer>> adjacentNodes = n.getAdjacentNodes();

            for (Node<Integer> a : adjacentNodes)
            {
                Point start = centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, n.getNodeId())];
                Point end = centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, a.getNodeId())];

                canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), mLinePaint);
            }
        }

        //This places the nodes and puts their value in their center (this assumes that the value of the nodes in the graph is unique!)
        for (int i = 0; i < numCircles; i++)
        {
            Node<Integer> n = layoutOrder.get(i);
            if (nodeToHighlight.getNodeId().equals(n.getNodeId()) && !target)
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mSecondPaint);
            } else if (nodeToHighlight.getNodeId().equals(n.getNodeId()) && target)
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mFoundPaint);
            } else
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mMainPaint);
            }

            canvas.drawText(Integer.toString(layoutOrder.get(i).getNodeValue()), centersOfCircles[i].getX(), centersOfCircles[i].getY(), mTextPaint);
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