package com.example.apphomepages.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.Datatypes.Color;
import com.example.apphomepages.Datatypes.Tuple;
import com.example.apphomepages.Drawable.ArrayQuicksortDrawable;
import com.example.apphomepages.Drawable.ArraySortDrawable;

import java.util.ArrayList;
import java.util.Arrays;

public class SortAnimations
{
    public static void generateBubbleSort(ArrayList<Integer> originalNumbers, ArrayList<Integer> squaresToHighlight, ArrayList<Tuple> iterations, ArrayList<Integer> numbers, ArraySortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 800;

        //setup main frame
        stopMotionAnimation[0] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), squaresToHighlight, -1, originalNumbers);

        for (int i = 1; i <= stopMotionAnimation.length - 2; i++)
        {
            stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), iterations.get(i - 1).constructPair(), -1, iterations.get(i - 1).getList());
        }

        stopMotionAnimation[stopMotionAnimation.length - 1] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), squaresToHighlight, iterations.size() - 1, iterations.get(stopMotionAnimation.length - 2).getList());

        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackgroundDrawable(animationDrawable);
    }

    public static void generateInsertionSort(ArrayList<Integer> originalNumbers, ArrayList<Integer> squaresToHighlight, ArrayList<Tuple> iterations, ArrayList<Integer> numbers, ArraySortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1000;

        int i = 0;
        stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), squaresToHighlight, -1, originalNumbers);
        i++;

        for (Tuple tuple : iterations)
        {
            stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Arrays.asList(tuple.getA()), tuple.getB(), tuple.getList());
            i++;
        }

        stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Arrays.asList(iterations.get(iterations.size() - 1).getA()), iterations.get(iterations.size() - 1).getB(), iterations.get(iterations.size() - 1).getList());

        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackgroundDrawable(animationDrawable);
    }

    public static void generateSelectionSort(ArrayList<Integer> originalNumbers, ArrayList<Integer> squaresToHighlight, ArrayList<Tuple> iterations, ArrayList<Integer> numbers, ArraySortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1000;

        int i = 0;
        stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), squaresToHighlight, -1, originalNumbers);
        i++;

        for (Tuple tuple : iterations)
        {
            stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Arrays.asList(tuple.getA()), tuple.getB(), tuple.getList());
            i++;
        }

        stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Arrays.asList(iterations.get(iterations.size() - 1).getA()), i - 1, iterations.get(iterations.size() - 1).getList());

        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackgroundDrawable(animationDrawable);

    }

    public static void generateQuicksort(ArrayList<Integer> originalNumbers, ArrayList<Tuple> iterations, ArrayQuicksortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1000;

        int i = 0;
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(-1);
        list1.add(-1);
        stopMotionAnimation[i] = new ArrayQuicksortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), -1, list1, originalNumbers);
        i++;

        for (Tuple tuple : iterations)
        {
            stopMotionAnimation[i] = new ArrayQuicksortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), tuple.getPivot(), tuple.constructPair(), tuple.getList());
            i++;
        }

        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.add(iterations.size());

        stopMotionAnimation[i] = new ArrayQuicksortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), -1, list2, iterations.get(iterations.size() - 1).getList());

        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackgroundDrawable(animationDrawable);

    }
}
