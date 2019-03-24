package com.example.apphomepages;

import android.content.Context;
import android.view.View;
import android.widget.Spinner;

import com.example.apphomepages.General.HelperMethods.HelperMethods;
import com.example.apphomepages.SearchAndSort.HelperMethods.SortHelperMethods;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Random;

public class SortHelperMethodsTest
{

    @Test
    public void populateSpinner()
    {
        View v = Mockito.mock(View.class);
        Context c = Mockito.mock(Context.class);
        Spinner s = new Spinner(c);
        ArrayList<Integer> integerArrayList = HelperMethods.generateRandomArray(new Random(), 4);
        SortHelperMethods.populateSpinner(integerArrayList, v, s, 0);

        Assert.assertEquals(0, s.getSelectedItemPosition());
    }
}