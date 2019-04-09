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

import static org.junit.Assert.assertEquals;

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

    @Test
    public void copyArray()
    {
        Integer[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);

        ArrayList<Integer> numbersArrayListComp = SortHelperMethods.copyArray(numbersArrayList1);

        for (int i = 0; i < numbersArrayList1.size(); i++)
            assertEquals(numbersArrayList1.get(i), numbersArrayListComp.get(i));
    }

    @Test
    public void copyArrayString()
    {
        Integer[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);

        ArrayList<String> numbersArrayListComp = SortHelperMethods.copyArrayString(numbersArrayList1, 0, numbersArrayList1.size() - 1);

        for (int i = 0; i < numbersArrayList1.size(); i++)
            assertEquals(numbersArrayList1.get(i).toString(), numbersArrayListComp.get(i));
    }
}