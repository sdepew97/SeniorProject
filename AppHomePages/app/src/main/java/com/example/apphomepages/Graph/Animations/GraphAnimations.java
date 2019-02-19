package com.example.apphomepages.Graph.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.Graph.Drawable.GraphSearchDrawable;

public class GraphAnimations
{
    public static void generateDepthFirstSearch(int locationInGraph, Graph graph, GraphSearchDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        /*
        //Duration
        int duration = 800;

        //Set up the main frame
        stopMotionAnimation[0] = new GraphSearchDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), -1, false, graph);

        for (int i = 1; i < stopMotionAnimation.length; i++)
        {
            stopMotionAnimation[i] = new GraphSearchDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), locationInGraph, true);
        }

        //Animate!
        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackgroundDrawable(animationDrawable);
        */
    }
}
