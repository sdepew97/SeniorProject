package com.example.apphomepages.Graph.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.DataTypes.TopologicalOrderingReturnType;
import com.example.apphomepages.Graph.Drawable.GraphSearchDrawable;
import com.example.apphomepages.Graph.Drawable.TopologicalOrderingDrawable;

import java.util.ArrayList;

public class GraphAnimations
{
    public static void generateGraphSearch(boolean found, ArrayList<Node<Integer>> nodesToHighlight, Graph<Integer> graph, GraphSearchDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 800;

        //Set up the main frame
        stopMotionAnimation[0] = new GraphSearchDrawable(new Node<Integer>(null, Integer.MAX_VALUE), false, graph);

        for (int i = 1; i < stopMotionAnimation.length - 1; i++)
        {
            stopMotionAnimation[i] = new GraphSearchDrawable(nodesToHighlight.get(i - 1), false, graph);
        }

        //Found or not?
        if (found)
        {
            stopMotionAnimation[stopMotionAnimation.length - 1] = new GraphSearchDrawable(nodesToHighlight.get(nodesToHighlight.size() - 1), true, graph);
        } else
        {
            stopMotionAnimation[stopMotionAnimation.length - 1] = new GraphSearchDrawable(new Node<Integer>(null, Integer.MAX_VALUE), false, graph);
        }

        //Animate!
        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackground(animationDrawable);
    }

    public static void generateTopologicalGraphOrdering(TopologicalOrderingReturnType topologicalOrderingReturnType, TopologicalOrderingDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1500;

        //Set up the main frame
        stopMotionAnimation[0] = new TopologicalOrderingDrawable(-1, topologicalOrderingReturnType.getGraphs().get(0), topologicalOrderingReturnType.getGraphs().get(0), topologicalOrderingReturnType.getVisitOrder(), true, false);

        for (int i = 1; i < stopMotionAnimation.length - 1; i++)
        {
            stopMotionAnimation[i] = new TopologicalOrderingDrawable(topologicalOrderingReturnType.getVisitOrder().get(i - 1), topologicalOrderingReturnType.getGraphs().get(i - 1), topologicalOrderingReturnType.getGraphs().get(0), topologicalOrderingReturnType.getVisitOrder(), false, false);
        }

        stopMotionAnimation[stopMotionAnimation.length - 1] = new TopologicalOrderingDrawable(-1, topologicalOrderingReturnType.getGraphs().get(0), topologicalOrderingReturnType.getGraphs().get(0), topologicalOrderingReturnType.getVisitOrder(), false, true);

        //Animate!
        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(true);
        image.setBackground(animationDrawable);
    }
}
