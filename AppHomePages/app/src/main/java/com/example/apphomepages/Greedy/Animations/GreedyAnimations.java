package com.example.apphomepages.Greedy.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.General.DataTypes.DijkstrasReturnType;
import com.example.apphomepages.Greedy.Drawable.DijkstrasDrawable;

import java.util.ArrayList;

public class GreedyAnimations
{
    public static void generateDijkstras(ArrayList<DijkstrasReturnType> frames, int[][] graph, DijkstrasDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 2000;

        for (int i = 0; i < stopMotionAnimation.length; i++)
        {
            stopMotionAnimation[i] = new DijkstrasDrawable(graph, frames.get(i));
        }

        //Animate!
        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(true);
        image.setBackground(animationDrawable);
    }
}
