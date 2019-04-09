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
    private final Paint mTextPaint;
    private final Paint mLinePaint;

    private Graph<String> graph;
    private Graph<String> originalGraph;
    private ArrayList<Integer> orderVisited;
    private Integer nodeToHighlightId;
    private boolean start;
    private boolean end;

    //An ArraySearchDrawable constructor for searching
    public TopologicalOrderingDrawable(Integer nodeToHighlightId, Graph<String> graph, Graph<String> originalGraph, ArrayList<Integer> orderVisited, boolean start, boolean end)
    {
        Color main = Color.getMain();
        Color secondary = Color.getSecondary();

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

        this.graph = graph;
        this.originalGraph = originalGraph;
        this.orderVisited = orderVisited;
        this.nodeToHighlightId = nodeToHighlightId;
        this.start = start;
        this.end = end;
    }

    @Override
    public void draw(Canvas canvas)
    {
        // Get the drawable's bounds
        int width = getBounds().width();
        int height = getBounds().height();
        int numCircles = graph.getGraphElements().size();
        int radius = (int) (height / (GraphHelperMethods.numLayers(originalGraph) + 4.0)); //the radius of the circle, since this means they will never overlap horizontally
        mTextPaint.setTextSize((float) (radius / 2.6));

        //Gets the center values of all the nodes and the correct value at the nodes in question
        Point[] centersOfCircles = GraphHelperMethods.placeNodes(originalGraph, width, height);
        ArrayList<Node<String>> layoutOrderOriginal = GraphAlgorithms.breadthFirstSearch(originalGraph, null, true); //this will do a breadth-first traversal, which is also how we are planning to lay out the nodes
        ArrayList<Node<String>> layoutOrder = GraphAlgorithms.breadthFirstSearch(graph, null, true); //this will do a breadth-first traversal, which is also how we are planning to lay out the nodes

        //Draw the lines connecting the nodes
        ArrayList<Node<String>> nodes = graph.getGraphElements();

        for (Node<String> n : nodes)
        {
            ArrayList<Node<String>> adjacentNodes = n.getAdjacentNodes();

            for (Node<String> a : adjacentNodes)
            {
                Point start = centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrderOriginal, n.getNodeId())];
                Point end = centersOfCircles[GraphHelperMethods.getNodeIndexBasedOnId(layoutOrderOriginal, a.getNodeId())];

                canvas.drawLine(start.getX(), start.getY(), end.getX(), end.getY(), mLinePaint);
                Matrix m = new Matrix();

                //I had to hardcode the values, here, because I couldn't figure out another way to give the proper values for the arrow's directions
                //Algebra 1 --> Geometry
                if (n.getNodeId() == 0 && a.getNodeId() == 1)
                {
                    m.setRotate(180, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                //Algebra 1 --> Algebra 2
                if (n.getNodeId() == 0 && a.getNodeId() == 2)
                {
                    m.setRotate(120, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                //Geometry --> Algebra 2
                if (n.getNodeId() == 1 && a.getNodeId() == 2)
                {
                    m.setRotate(90, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                //Algebra 2 --> Trigonometry
                if (n.getNodeId() == 2 && a.getNodeId() == 3)
                {
                    m.setRotate(240, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                //Trigonometry --> Pre-Calculus
                if (n.getNodeId() == 3 && a.getNodeId() == 6)
                {
                    m.setRotate(60, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                //Statistics --> Pre-Calculus
                if (n.getNodeId() == 4 && a.getNodeId() == 6)
                {
                    m.setRotate(120, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                //Probability --> Pre-Calculus
                if (n.getNodeId() == 5 && a.getNodeId() == 6)
                {
                    m.setRotate(180, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                //Pre-Calculus --> Calculus I
                if (n.getNodeId() == 6 && a.getNodeId() == 7)
                {
                    m.setRotate(240, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                //Pre-Calculus --> AP Calculus
                if (n.getNodeId() == 6 && a.getNodeId() == 8)
                {
                    m.setRotate(0, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)));
                }

                drawTriangle(canvas, mTextPaint, ((int) ((start.getX() + end.getX()) / 2.0)), ((int) ((start.getY() + end.getY()) / 2.0)), 15, m);
            }
        }

        //This places the nodes and puts their value in their center (this assumes that the value of the nodes in the graph is unique!)
        for (int i = 0; i < numCircles; i++)
        {
            int x = GraphHelperMethods.getNodeIndexBasedOnId(layoutOrderOriginal, layoutOrder.get(i).getNodeId());

            if (nodeToHighlightId.equals(layoutOrder.get(i).getNodeId()) && !start && !end)
            {
                canvas.drawCircle(centersOfCircles[x].getX(), centersOfCircles[x].getY(), radius, mSecondPaint);
            } else
            {
                canvas.drawCircle(centersOfCircles[x].getX(), centersOfCircles[x].getY(), radius, mMainPaint);
            }

            if (!end)
            {
                canvas.drawText(layoutOrder.get(i).getNodeValue(), centersOfCircles[x].getX(), centersOfCircles[x].getY(), mTextPaint);
            } else
            {
                canvas.drawText(layoutOrder.get(i).getNodeValue() + " (" + (orderVisited.indexOf(layoutOrder.get(i).getNodeId()) + 1) + ")", centersOfCircles[x].getX(), centersOfCircles[x].getY(), mTextPaint);
            }
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
    private void drawTriangle(Canvas canvas, Paint paint, int x, int y, int width, Matrix m)
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