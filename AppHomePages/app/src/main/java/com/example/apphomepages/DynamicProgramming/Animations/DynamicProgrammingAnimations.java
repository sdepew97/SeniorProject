package com.example.apphomepages.DynamicProgramming.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.DynamicProgramming.Drawable.MinEditDrawable;
import com.example.apphomepages.General.DataTypes.MinEditReturnType;

import java.util.ArrayList;

public class DynamicProgrammingAnimations
{
    public static void generateMinEditDistance(ArrayList<MinEditReturnType> frames, String target, String source, MinEditDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1500;

        stopMotionAnimation[0] = new MinEditDrawable(frames.get(0), target, source, true); //start...so special case!

        for (int i = 1; i < stopMotionAnimation.length; i++)
        {
            stopMotionAnimation[i] = new MinEditDrawable(frames.get(i), target, source, false);
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
