package com.example.apphomepages;

import com.example.apphomepages.General.Helpers.HelperMethods;
import com.example.apphomepages.SearchAndSort.Algorithms.SearchingAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SearchingAlgorithmsTest
{

    @Test
    public void linearSearch()
    {
        int[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);
        int target1 = 22;
        int target2 = 12;
        int target3 = 452;

        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> numbersArrayList2 = HelperMethods.convertFromArray(numbers2);
        int target4 = 1;
        int target5 = 8;

        int target6 = -42; //this value is not in either array

        assertEquals(4, SearchingAlgorithms.linearSearch(numbersArrayList1, target1));
        assertEquals(0, SearchingAlgorithms.linearSearch(numbersArrayList1, target2));
        assertEquals(numbersArrayList1.size() - 1, SearchingAlgorithms.linearSearch(numbersArrayList1, target3));
        assertEquals(-1, SearchingAlgorithms.linearSearch(numbersArrayList1, target6));

        assertEquals(0, SearchingAlgorithms.linearSearch(numbersArrayList2, target4));
        assertEquals(numbersArrayList2.size() - 1, SearchingAlgorithms.linearSearch(numbersArrayList2, target5));
        assertEquals(-1, SearchingAlgorithms.linearSearch(numbersArrayList2, target6));
    }

    @Test
    public void binarySearchWithLocations()
    {
        //Here I compare the result of my binary search with the result of the Arrays class binary search method

        int[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        Arrays.sort(numbers1); //precondition
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);
        int target1 = 22;
        int target2 = 12;
        int target3 = 452;

        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8};
        Arrays.sort(numbers2); //precondition
        ArrayList<Integer> numbersArrayList2 = HelperMethods.convertFromArray(numbers2);
        int target4 = 1;
        int target5 = 8;

        int target6 = -42; //this value is not in either array

        ArrayList<Integer> result1 = SearchingAlgorithms.binarySearchWithLocations(numbersArrayList1, target1);
        ArrayList<Integer> result2 = SearchingAlgorithms.binarySearchWithLocations(numbersArrayList1, target2);
        ArrayList<Integer> result3 = SearchingAlgorithms.binarySearchWithLocations(numbersArrayList1, target3);
        ArrayList<Integer> result4 = SearchingAlgorithms.binarySearchWithLocations(numbersArrayList1, target6);

        ArrayList<Integer> result5 = SearchingAlgorithms.binarySearchWithLocations(numbersArrayList2, target4);
        ArrayList<Integer> result6 = SearchingAlgorithms.binarySearchWithLocations(numbersArrayList2, target5);
        ArrayList<Integer> result7 = SearchingAlgorithms.binarySearchWithLocations(numbersArrayList2, target6);

        assertEquals(Integer.toString(Arrays.binarySearch(numbers1, target1)), Integer.toString(result1.get(result1.size() - 1)));
        assertEquals(Integer.toString(Arrays.binarySearch(numbers1, target2)), Integer.toString(result2.get(result2.size() - 1)));
        assertEquals(Integer.toString(Arrays.binarySearch(numbers1, target3)), Integer.toString(result3.get(result3.size() - 1)));
        assertEquals(Integer.toString(Arrays.binarySearch(numbers1, target6)), Integer.toString(result4.get(result4.size() - 1)));

        assertEquals(Integer.toString(Arrays.binarySearch(numbers2, target4)), Integer.toString(result5.get(result5.size() - 1)));
        assertEquals(Integer.toString(Arrays.binarySearch(numbers2, target5)), Integer.toString(result6.get(result6.size() - 1)));
        assertEquals(Integer.toString(Arrays.binarySearch(numbers2, target6)), Integer.toString(result7.get(result7.size() - 1)));
    }
}