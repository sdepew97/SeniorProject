package com.example.apphomepages.Greedy.Drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.DijkstrasReturnType;
import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.DataTypes.Point;
import com.example.apphomepages.Graph.Algorithms.GraphAlgorithms;
import com.example.apphomepages.Graph.HelperMethods.GraphHelperMethods;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class DijkstrasDrawable extends Drawable implements Animatable
{
    private final Paint mInitialPaint;
    private final Paint mMarkedPaint;
    private final Paint mCurrentPaint;
    private final Paint mLookAtPaint;
    private final Paint mScanPaint;

    private final Paint mTextPaint;
    private final Paint mLinePaint;

    private int[][] graph;
    private DijkstrasReturnType frameToAnimate;

    public DijkstrasDrawable(int[][] graph, DijkstrasReturnType frameToAnimate)
    {
        Color initial = Color.getMain();
        Color marked = Color.getSecondary();
        Color current = Color.getCurrent();
        Color lookAt = Color.getFound();
        Color scan = Color.getScan();

        // Set up color and text size
        mInitialPaint = new Paint();
        mInitialPaint.setARGB(255, initial.getRed(), initial.getGreen(), initial.getBlue());

        mMarkedPaint = new Paint();
        mMarkedPaint.setARGB(255, marked.getRed(), marked.getGreen(), marked.getBlue());

        mCurrentPaint = new Paint();
        mCurrentPaint.setARGB(255, current.getRed(), current.getGreen(), current.getBlue());

        mLookAtPaint = new Paint();
        mLookAtPaint.setARGB(255, lookAt.getRed(), lookAt.getGreen(), lookAt.getBlue());

        mScanPaint = new Paint();
        mScanPaint.setARGB(255, scan.getRed(), scan.getGreen(), scan.getBlue());

        mTextPaint = new Paint();
        mTextPaint.setARGB(255, 0, 0, 0);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mLinePaint = new Paint();
        mLinePaint.setARGB(255, 0, 0, 0);
        mLinePaint.setTextAlign(Paint.Align.CENTER);
        mLinePaint.setStyle(Paint.Style.FILL);
        mLinePaint.setStrokeWidth(2);

        this.graph = graph;
        this.frameToAnimate = frameToAnimate;
    }

    @Override
    public void draw(Canvas canvas)
    {
        //convert int[][] graph to adjacency list version of the graph...
        ArrayList<Node<Integer>> nodesA = new ArrayList<>();
        for (int j = 0; j < graph.length; j++)
        {
            nodesA.add(new Node<>(j, j));
        }

        for (int k = 0; k < graph.length; k++)
        {
            for (int l = 0; l < graph[k].length; l++)
            {
                if (graph[k][l] != 0)
                {
                    nodesA.get(k).addAdjacentNode(nodesA.get(l));
                }
            }
        }

        Graph<Integer> graphA = new Graph<>(nodesA);

        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numCircles = graph.length;
        int radius = height / (GraphHelperMethods.numLayers(graphA) * 2 + 4); //the radius of the circle, since this means they will never overlap horizontally
        mTextPaint.setTextSize(radius * 3 / 4);

        //Gets the center values of all the nodes and the correct value at the nodes in question
        Point[] centersOfCircles = GraphHelperMethods.placeNodes(graphA, width, height);
        ArrayList<Node<Integer>> layoutOrder = GraphAlgorithms.breadthFirstSearch(graphA, null, true); //this will do a breadth-first traversal, which is also how we are planning to lay out the nodes

        //Draw the lines connecting the nodes
        ArrayList<Node<Integer>> nodes = graphA.getGraphElements();

        for (Node<Integer> n : nodes)
        {
            ArrayList<Node<Integer>> adjacentNodes = n.getAdjacentNodes();

            for (Node<Integer> a : adjacentNodes)
            {
                Point start = centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, n.getNodeId())];
                Point end = centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, a.getNodeId())];

                canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), mLinePaint);

                //Edge weights
                canvas.drawText(Integer.toString(graph[n.getNodeId()][a.getNodeId()]), (float) (Math.abs((centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, a.getNodeId())].getX() + centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, n.getNodeId())].getX())) / 2.0) - (radius / 2), (float) (Math.abs((centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, a.getNodeId())].getY() + centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrder, n.getNodeId())].getY())) / 2.0), mTextPaint);
            }
        }

        //This places the nodes and puts their value in their center (this assumes that the value of the nodes in the graph is unique!)
        for (int i = 0; i < numCircles; i++)
        {
            if (frameToAnimate.getProcessed()[layoutOrder.get(i).getNodeId()] && (frameToAnimate.getNodeIDBeingScanned() == layoutOrder.get(i).getNodeId() || frameToAnimate.getMinimumNodeId() == layoutOrder.get(i).getNodeId()))
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mCurrentPaint);
            } else if (frameToAnimate.getProcessed()[layoutOrder.get(i).getNodeId()])
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mMarkedPaint);
            } else if (frameToAnimate.isScanningForMin() && frameToAnimate.getNodeIDBeingScanned() == layoutOrder.get(i).getNodeId())
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mScanPaint);
            } else if (frameToAnimate.getNodeIDBeingScanned() == layoutOrder.get(i).getNodeId())
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mLookAtPaint);
            } else
            {
                canvas.drawCircle(centersOfCircles[i].getX(), centersOfCircles[i].getY(), radius, mInitialPaint);
            }

            if (frameToAnimate.getDistances()[layoutOrder.get(i).getNodeId()] == Integer.MAX_VALUE)
            {
                //used https://stackoverflow.com/questions/10806893/how-can-i-get-the-unicode-infinity-symbol-converted-to-string for the infinity symbol
                canvas.drawText("(" + Integer.toString(layoutOrder.get(i).getNodeId()) + "," + String.valueOf(Character.toString('\u221E')) + ")", centersOfCircles[i].getX(), centersOfCircles[i].getY(), mTextPaint);
            } else
            {
                //Node ID, Node Distance Value
                canvas.drawText("(" + Integer.toString(layoutOrder.get(i).getNodeId()) + "," + Integer.toString(frameToAnimate.getDistances()[layoutOrder.get(i).getNodeId()]) + ")", centersOfCircles[i].getX(), centersOfCircles[i].getY(), mTextPaint);
            }
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
