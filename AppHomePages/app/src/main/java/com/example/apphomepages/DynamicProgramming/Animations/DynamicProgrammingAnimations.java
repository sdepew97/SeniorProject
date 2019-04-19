package com.example.apphomepages.DynamicProgramming.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.DynamicProgramming.Drawable.LevenshteinDrawable;
import com.example.apphomepages.General.DataTypes.LevenshteinReturnType;

import java.util.ArrayList;

public class DynamicProgrammingAnimations
{
    public static void generateLevenshteinDistance(ArrayList<LevenshteinReturnType> frames, String target, String source, LevenshteinDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1500;

        stopMotionAnimation[0] = new LevenshteinDrawable(frames.get(0), target, source, true); //start...so special case!

        for (int i = 1; i < stopMotionAnimation.length; i++)
        {
            stopMotionAnimation[i] = new LevenshteinDrawable(frames.get(i), target, source, false);
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
