package com.example.apphomepages.SearchAndSort.HelperMethods;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.apphomepages.General.HelperMethods.HelperMethods;

import java.util.ArrayList;

public class SortHelperMethods
{
    public static void populateSpinner(ArrayList<Integer> numbers, View view, Spinner spinner, int index)
    {
        //populate the spinner used https://stackoverflow.com/questions/20244419/android-spinner-populating-using-arrayliststring as a resource as well as https://stackoverflow.com/questions/10582283/how-to-populate-spinner-from-a-array-string
        ArrayList<String> numbersStrings = HelperMethods.convertToStrings(numbers);
        ArrayAdapter adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, numbersStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Set spinner selection
        spinner.setSelection(index); //choose the element in the array to select automatically from the dropdown menu (a.k.a. spinner menu)
    }

    public static ArrayList<Integer> copyArray(ArrayList<Integer> arrayToCopy)
    {
        ArrayList<Integer> arrayWithResult = new ArrayList<>();

        for (int i = 0; i < arrayToCopy.size(); i++)
            arrayWithResult.add(arrayToCopy.get(i));

        return arrayWithResult;
    }

    //Copies an array list from left to right inclusive of right into a string type
    public static ArrayList<String> copyArrayString(ArrayList<Integer> arrayToCopy, int left, int right)
    {
        ArrayList<String> arrayWithResult = new ArrayList<>();

        for (int i = left; i <= right; i++)
        {
            arrayWithResult.add(arrayToCopy.get(i).toString());
        }

        return arrayWithResult;
    }
}
