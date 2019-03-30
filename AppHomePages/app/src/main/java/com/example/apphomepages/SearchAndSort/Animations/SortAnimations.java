package com.example.apphomepages.SearchAndSort.Animations;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.QuickSortReturnType;
import com.example.apphomepages.General.DataTypes.SortReturnType;
import com.example.apphomepages.SearchAndSort.Drawable.ArrayMergeSortDrawable;
import com.example.apphomepages.SearchAndSort.Drawable.ArrayQuicksortDrawable;
import com.example.apphomepages.SearchAndSort.Drawable.ArraySortDrawable;

import java.util.ArrayList;
import java.util.Arrays;

public class SortAnimations
{
    public static void generateBubbleSort(ArrayList<Integer> originalNumbers, ArrayList<Integer> squaresToHighlight, ArrayList<SortReturnType> iterations, ArrayList<Integer> numbers, ArraySortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
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

    public static void generateInsertionSort(ArrayList<Integer> originalNumbers, ArrayList<Integer> squaresToHighlight, ArrayList<SortReturnType> iterations, ArrayList<Integer> numbers, ArraySortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1000;

        int i = 0;
        stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), squaresToHighlight, -1, originalNumbers);
        i++;

        for (SortReturnType sortReturnType : iterations)
        {
            stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Arrays.asList(sortReturnType.getA()), sortReturnType.getB(), sortReturnType.getList());
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

    public static void generateSelectionSort(ArrayList<Integer> originalNumbers, ArrayList<Integer> squaresToHighlight, ArrayList<SortReturnType> iterations, ArrayList<Integer> numbers, ArraySortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1000;

        int i = 0;
        stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), squaresToHighlight, -1, originalNumbers);
        i++;

        for (SortReturnType sortReturnType : iterations)
        {
            stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Arrays.asList(sortReturnType.getA()), sortReturnType.getB(), sortReturnType.getList());
            i++;
        }

        stopMotionAnimation[i] = new ArraySortDrawable(Color.getMain(), Color.getSecondary(), Color.getFound(), Arrays.asList(iterations.get(iterations.size() - 1).getA()), i - 1, iterations.get(iterations.size() - 1).getList());

        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackground(animationDrawable);

    }

    public static void generateQuicksort(ArrayList<QuickSortReturnType> iterations, ArrayQuicksortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1500;

        //Start with no highlighted
        stopMotionAnimation[0] = new ArrayQuicksortDrawable(true, false, iterations.get(0));

        for (int i = 1; i < iterations.size() - 1; i++)
        {
            QuickSortReturnType quickSortReturnType = iterations.get(i);
            stopMotionAnimation[i] = new ArrayQuicksortDrawable(false, false, quickSortReturnType);
        }

        //End with all highlighted
        stopMotionAnimation[iterations.size() - 1] = new ArrayQuicksortDrawable(false, true, iterations.get(iterations.size() - 1));

        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackground(animationDrawable);

    }

    public static void generateMergeSort(ArrayList<ArrayList<ArrayList<String>>> iterationValues, ArrayMergeSortDrawable[] stopMotionAnimation, ImageView image, AnimationDrawable animationDrawable)
    {
        //Duration
        int duration = 1500;

        int i = 0;
        for (int j = 0; j < iterationValues.size() - 1; j++)
        {
            ArrayList<ArrayList<String>> iteration = iterationValues.get(j);
            stopMotionAnimation[i] = new ArrayMergeSortDrawable(Color.getMain(), Color.getSecondary(), iteration, false);
            i++;
        }

        ArrayList<ArrayList<String>> iteration = iterationValues.get(iterationValues.size() - 1);
        stopMotionAnimation[i] = new ArrayMergeSortDrawable(Color.getMain(), Color.getSecondary(), iteration, true);

        for (Drawable d : stopMotionAnimation)
        {
            animationDrawable.addFrame(d, duration);
        }

        animationDrawable.setOneShot(false);
        image.setBackground(animationDrawable);
    }
}
