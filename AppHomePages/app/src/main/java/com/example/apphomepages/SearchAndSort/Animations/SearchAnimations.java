package com.example.apphomepages.SearchAndSort.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.SearchAndSort.Drawable.ArraySearchDrawable;

import java.util.ArrayList;

public class SearchAnimations
{
    public static void generateLinearSearch(int locationInArray, ArrayList<Integer> numbers, ArraySearchDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 800;

        //Set up the main frame
        stopMotionAnimation[0] = new ArraySearchDrawable(-1, false, numbers);

        for (int i = 1; i < stopMotionAnimation.length; i++)
        {
            stopMotionAnimation[i] = locationInArray == (i - 1) ? new ArraySearchDrawable(i - 1, true, numbers) : new ArraySearchDrawable(i - 1, false, numbers);
        }

        //Animate!
        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackground(animationDrawable);
    }

    public static void generateBinarySearch(int locationInArray, ArrayList<Integer> squaresToHighlight, ArrayList<Integer> numbers, ArraySearchDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 800;

        //Set up the main frame
        stopMotionAnimation[0] = new ArraySearchDrawable(-1, false, numbers);

        int index = 1;
        for (Integer i : squaresToHighlight)
        {
            stopMotionAnimation[index] = ((locationInArray == i) && (index == squaresToHighlight.size())) ? new ArraySearchDrawable(i, true, numbers) : new ArraySearchDrawable(i, false, numbers);
            index++;
        }

        //Animate!
        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackground(animationDrawable);
    }
}
