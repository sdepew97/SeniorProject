package com.example.apphomepages.SearchAndSort.HelperMethods;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.apphomepages.General.Helpers.HelperMethods;

import java.util.ArrayList;
import java.util.List;

public class SearchHelperMethods
{
    public SearchHelperMethods()
    {
    }

    public static void populateSpinner(List<Integer> numbers, View viewGlobal, Spinner spinner, Integer locationInArray)
    {
        //populate the spinner used https://stackoverflow.com/questions/20244419/android-spinner-populating-using-arrayliststring as a resource as well as https://stackoverflow.com/questions/10582283/how-to-populate-spinner-from-a-array-string
        ArrayList<String> numbersStrings = HelperMethods.convertToStrings(numbers);
        numbersStrings.add(0, "Not Found");
        ArrayAdapter adapter = new ArrayAdapter<>(viewGlobal.getContext(), android.R.layout.simple_spinner_item, numbersStrings);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Set spinner selection
        if (locationInArray != -1)
        {
            spinner.setSelection(locationInArray + 1); //choose the element in the array to select automatically from the dropdown menu (a.k.a. spinner menu)
        } else
        {
            spinner.setSelection(0); //This is the element that says "not found"
        }
    }
}
