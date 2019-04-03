package com.example.apphomepages.DynamicProgramming.HelperMethods;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.apphomepages.General.HelperMethods.HelperMethods;

import java.util.ArrayList;

public class DynamicProgrammingHelperMethods
{
    //The input to this method must be an NxN array
    //TODO: Ask richard about this method from a design perspective
    public static boolean[][] copy2DArray(boolean[][] source, int N)
    {
        boolean[][] copy = new boolean[N][N]; //the input must be a nxn array

        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                copy[i][j] = source[i][j];
            }
        }

        return copy;
    }

    public static void populateSpinner(int[] boardSizes, View viewGlobal, Spinner spinner, int locationInArray)
    {
        ArrayList<Integer> boardSizesArrayList = new ArrayList<>();
        for (int i : boardSizes)
            boardSizesArrayList.add(i);

        //populate the spinner used https://stackoverflow.com/questions/20244419/android-spinner-populating-using-arrayliststring as a resource as well as https://stackoverflow.com/questions/10582283/how-to-populate-spinner-from-a-array-string
        ArrayList<String> numbersStrings = HelperMethods.convertToStrings(boardSizesArrayList);

        ArrayAdapter adapter = new ArrayAdapter<>(viewGlobal.getContext(), android.R.layout.simple_spinner_item, numbersStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setSelection(locationInArray); //choose the element in the array to select automatically from the dropdown menu (a.k.a. spinner menu)
    }
}
