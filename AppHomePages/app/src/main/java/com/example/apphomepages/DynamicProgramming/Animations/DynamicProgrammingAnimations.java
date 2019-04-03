package com.example.apphomepages.DynamicProgramming.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.DynamicProgramming.Drawable.NQueensDrawable;
import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.NQueensReturnType;

import java.util.ArrayList;

public class DynamicProgrammingAnimations
{
    public static void generateNQueens(ArrayList<NQueensReturnType> frames, NQueensDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1000;

        for (int i = 0; i < stopMotionAnimation.length; i++)
        {
            stopMotionAnimation[i] = new NQueensDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Color.getCurrent(), frames.get(i));
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
