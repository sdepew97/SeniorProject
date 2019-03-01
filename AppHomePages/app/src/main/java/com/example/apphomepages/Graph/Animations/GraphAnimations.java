package com.example.apphomepages.Graph.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.Graph.Drawable.GraphSearchDrawable;
import com.example.apphomepages.Graph.Drawable.TopologicalOrderingDrawable;

import java.util.ArrayList;

public class GraphAnimations
{
    public static void generateGraphSearch(boolean found, ArrayList<Integer> nodesToHighlight, Graph graph, GraphSearchDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 800;

        //Set up the main frame
        stopMotionAnimation[0] = new GraphSearchDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Integer.MAX_VALUE, false, graph); //TODO: talk with richard about this...this is the -1 issue!

        for (int i = 1; i < stopMotionAnimation.length - 1; i++)
        {
            stopMotionAnimation[i] = new GraphSearchDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), nodesToHighlight.get(i - 1), false, graph);
        }

        //Found or not?
        if (found)
        {
            stopMotionAnimation[stopMotionAnimation.length - 1] = new GraphSearchDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), nodesToHighlight.get(nodesToHighlight.size() - 1), true, graph);
        } else
        {
            stopMotionAnimation[stopMotionAnimation.length - 1] = new GraphSearchDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Integer.MAX_VALUE, false, graph);
        }

        //Animate!
        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackgroundDrawable(animationDrawable);
    }

    public static void generateTopologicalGraphOrdering(ArrayList<String> nodesToHighlight, Graph<String> graph, TopologicalOrderingDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 800;

        //Set up the main frame
        stopMotionAnimation[0] = new TopologicalOrderingDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), "", graph, true);

        for (int i = 1; i < stopMotionAnimation.length - 1; i++)
        {
            stopMotionAnimation[i] = new TopologicalOrderingDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), nodesToHighlight.get(i - 1), graph, false);
        }

        stopMotionAnimation[stopMotionAnimation.length - 1] = new TopologicalOrderingDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), "", graph, true);


        //Animate!
        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackgroundDrawable(animationDrawable);
    }
}
