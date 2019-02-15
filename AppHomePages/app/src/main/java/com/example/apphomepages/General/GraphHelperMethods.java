package com.example.apphomepages.General;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class GraphHelperMethods
{
    public static void populateSpinner(ArrayList<String> possibleTreeTypes, View view, Spinner spinner, int index)
    {
        //populate the spinner used https://stackoverflow.com/questions/20244419/android-spinner-populating-using-arrayliststring as a resource as well as https://stackoverflow.com/questions/10582283/how-to-populate-spinner-from-a-array-string
        ArrayAdapter adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, possibleTreeTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Set spinner selection
        spinner.setSelection(index); //choose the element in the array to select automatically from the dropdown menu (a.k.a. spinner menu)
    }
}
