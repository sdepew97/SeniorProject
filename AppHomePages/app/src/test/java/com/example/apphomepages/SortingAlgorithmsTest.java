package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.MergeSortReturnType;
import com.example.apphomepages.General.DataTypes.Tuple;
import com.example.apphomepages.General.Helpers.HelperMethods;
import com.example.apphomepages.SearchAndSort.Algorithms.SortingAlgorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class SortingAlgorithmsTest
{
    @Test
    public void bubbleSort()
    {
        int[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);

        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> numbersArrayList2 = HelperMethods.convertFromArray(numbers2);

        ArrayList<Tuple> resultOfBubbleOn1 = SortingAlgorithms.bubbleSort(numbersArrayList1);
        ArrayList<Tuple> resultOfBubbleOn2 = SortingAlgorithms.bubbleSort(numbersArrayList2);

        Arrays.sort(numbers1);
        Arrays.sort(numbers2);

        for (int i = 0; i < numbers1.length; i++)
            assertEquals(new Integer(numbers1[i]), resultOfBubbleOn1.get(resultOfBubbleOn1.size() - 1).getList().get(i));

        for (int j = 0; j < numbers2.length; j++)
            assertEquals(new Integer(numbers2[j]), resultOfBubbleOn2.get(resultOfBubbleOn2.size() - 1).getList().get(j));
    }

    @Test
    public void copyArray()
    {
        int[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);

        ArrayList<Integer> numbersArrayListComp = SortingAlgorithms.copyArray(numbersArrayList1);

        for (int i = 0; i < numbersArrayList1.size(); i++)
            assertEquals(numbersArrayList1.get(i), numbersArrayListComp.get(i));
    }

    @Test
    public void insertionSort()
    {
        int[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);

        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> numbersArrayList2 = HelperMethods.convertFromArray(numbers2);

        ArrayList<Tuple> resultOfInsertionOn1 = SortingAlgorithms.insertionSort(numbersArrayList1);
        ArrayList<Tuple> resultOfInsertionOn2 = SortingAlgorithms.insertionSort(numbersArrayList2);

        Arrays.sort(numbers1);
        Arrays.sort(numbers2);

        for (int i = 0; i < numbers1.length; i++)
            assertEquals(new Integer(numbers1[i]), resultOfInsertionOn1.get(resultOfInsertionOn1.size() - 1).getList().get(i));

        for (int j = 0; j < numbers2.length; j++)
            assertEquals(new Integer(numbers2[j]), resultOfInsertionOn2.get(resultOfInsertionOn2.size() - 1).getList().get(j));
    }

    @Test
    public void selectionSort()
    {
        int[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);

        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> numbersArrayList2 = HelperMethods.convertFromArray(numbers2);

        ArrayList<Tuple> resultOfSelectionOn1 = SortingAlgorithms.insertionSort(numbersArrayList1);
        ArrayList<Tuple> resultOfSelectionOn2 = SortingAlgorithms.insertionSort(numbersArrayList2);

        Arrays.sort(numbers1);
        Arrays.sort(numbers2);

        for (int i = 0; i < numbers1.length; i++)
            assertEquals(new Integer(numbers1[i]), resultOfSelectionOn1.get(resultOfSelectionOn1.size() - 1).getList().get(i));

        for (int j = 0; j < numbers2.length; j++)
            assertEquals(new Integer(numbers2[j]), resultOfSelectionOn2.get(resultOfSelectionOn2.size() - 1).getList().get(j));
    }

    @Test
    public void merge()
    {
        int[] numbers1 = {-1, 1, 12, 23, 24, 22, 86, 123};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);
        ArrayList<MergeSortReturnType> merged = SortingAlgorithms.merge(numbersArrayList1, 0, 4, numbers1.length - 1, Color.randomColor());

        Arrays.sort(numbers1);

        for (int i = 0; i < numbers1.length; i++)
            assertEquals(new Integer(numbers1[i]), merged.get(merged.size() - 1).getNumbers().get(i));
    }

    @Test
    public void mergeSort()
    {
        int[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);

        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> numbersArrayList2 = HelperMethods.convertFromArray(numbers2);

        ArrayList<MergeSortReturnType> resultOfMergeOn1 = SortingAlgorithms.mergeSort(numbersArrayList1, 0, numbersArrayList1.size() - 1);
        ArrayList<MergeSortReturnType> resultOfMergeOn2 = SortingAlgorithms.mergeSort(numbersArrayList2, 0, numbersArrayList2.size() - 1);

        Arrays.sort(numbers1);
        Arrays.sort(numbers2);

        for (int i = 0; i < numbers1.length; i++)
            assertEquals(new Integer(numbers1[i]), resultOfMergeOn1.get(resultOfMergeOn1.size() - 1).getNumbers().get(i));

        for (int j = 0; j < numbers2.length; j++)
            assertEquals(new Integer(numbers2[j]), resultOfMergeOn2.get(resultOfMergeOn2.size() - 1).getNumbers().get(j));
    }

    @Test
    public void quicksort()
    {
        int[] numbers1 = {12, 1, 23, -1, 22, 142, 122, 452};
        ArrayList<Integer> numbersArrayList1 = HelperMethods.convertFromArray(numbers1);

        int[] numbers2 = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> numbersArrayList2 = HelperMethods.convertFromArray(numbers2);

        ArrayList<Tuple> resultOfQuickOn1 = SortingAlgorithms.quicksort(numbersArrayList1, 0, numbersArrayList1.size() - 1);
        ArrayList<Tuple> resultOfQuickOn2 = SortingAlgorithms.quicksort(numbersArrayList2, 0, numbersArrayList2.size() - 1);

        Arrays.sort(numbers1);
        Arrays.sort(numbers2);

        for (int i = 0; i < numbers1.length; i++)
            assertEquals(new Integer(numbers1[i]), resultOfQuickOn1.get(resultOfQuickOn1.size() - 1).getList().get(i));

        for (int j = 0; j < numbers2.length; j++)
            assertEquals(new Integer(numbers2[j]), resultOfQuickOn2.get(resultOfQuickOn2.size() - 1).getList().get(j));
    }
}