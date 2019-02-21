package com.example.apphomepages.SearchAndSort.Algorithms;

import com.example.apphomepages.General.DataTypes.Color;
import com.example.apphomepages.General.DataTypes.MergeSortReturnType;
import com.example.apphomepages.General.DataTypes.PairOfTuple;
import com.example.apphomepages.General.DataTypes.Tuple;

import java.util.ArrayList;

public class SortingAlgorithms
{
    //Default constructor
    public SortingAlgorithms()
    {
        //Do nothing
    }

    //Bubble mergeSort adapted from https://www.geeksforgeeks.org/bubble-sort/
    public static ArrayList<Tuple> bubbleSort(ArrayList<Integer> arr)
    {
        ArrayList<Tuple> resultsOfSteps = new ArrayList<>();

        int n = arr.size();
        for (int i = 0; i < n - 1; i++)
        {
            for (int j = 0; j < n - i - 1; j++)
            {
                if (arr.get(j) > arr.get(j + 1))
                {
                    Tuple t1 = new Tuple();
                    Tuple t2 = new Tuple();
                    // swap arr[j+1] and arr[j]
                    t1.setA(j + 1);
                    t2.setA(j + 1);
                    t1.setB(j);
                    t2.setB(j);
                    t1.setList(copyArray(arr));
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    t2.setList(copyArray(arr));
                    resultsOfSteps.add(t1);
                    resultsOfSteps.add(t2);
                } else
                {
                    Tuple t1 = new Tuple();
                    t1.setA(j + 1);
                    t1.setB(j);
                    t1.setList(copyArray(arr));
                    resultsOfSteps.add(t1);
                    resultsOfSteps.add(t1);
                }
            }
        }

        resultsOfSteps.add(new Tuple(arr));
        resultsOfSteps.add(new Tuple(arr));
        return resultsOfSteps;
    }

    public static ArrayList<Integer> copyArray(ArrayList<Integer> arrayToCopy)
    {
        ArrayList<Integer> arrayWithResult = new ArrayList<>();

        for (Integer i : arrayToCopy)
        {
            arrayWithResult.add(i);
        }

        return arrayWithResult;
    }

    //Insertion mergeSort adapted from https://www.geeksforgeeks.org/insertion-sort/
    public static ArrayList<Tuple> insertionSort(ArrayList<Integer> arr)
    {
        ArrayList<Tuple> resultOfSteps = new ArrayList<>();

        int n = arr.size();

        //Initial array
        Tuple t = new Tuple();
        t.setList(copyArray(arr));
        t.setA(-1);
        t.setB(1);
        resultOfSteps.add(t);

        for (int i = 1; i < n; ++i)
        {
            int key = arr.get(i);
            int j = i - 1;

            Tuple t1 = new Tuple();
            t1.setA(j + 1);
            t1.setList(copyArray(arr));
            t1.setB(i + 1);
            resultOfSteps.add(t1);

            /*
             * Move elements of arr[0..i-1], that are
             * greater than key, to one position ahead
             * of their current position
             */
            while (j >= 0 && arr.get(j) > key)
            {
                arr.set(j + 1, arr.get(j));
                j = j - 1;
                Tuple tuple = new Tuple();
                tuple.setList(copyArray(arr));
                tuple.setA(-1);
                tuple.setB(i + 1);
                resultOfSteps.add(tuple);
            }
            Tuple t2 = new Tuple();
            t2.setA(j + 1);
            arr.set(j + 1, key);

            t2.setB(i + 1);
            t2.setList(copyArray(arr));
            resultOfSteps.add(t2);
        }

        Tuple finalTuple = new Tuple();
        finalTuple.setList(copyArray(arr));
        finalTuple.setB(n);
        resultOfSteps.add(finalTuple); //This is the sorted array...

        return resultOfSteps;

    }

    //Selection mergeSort adapted from https://www.geeksforgeeks.org/selection-sort/
    public static ArrayList<Tuple> selectionSort(ArrayList<Integer> arr)
    {
        ArrayList<Tuple> resultOfSteps = new ArrayList<>();

        int n = arr.size();

        // One by one move boundary of unsorted sub-array
        for (int i = 0; i < n; i++)
        {
            // Frame with the minimum
            Tuple t = new Tuple();
            t.setList(copyArray(arr));
            t.setB(i);

            // Find the minimum element in unsorted array
            // Frame with the first search value
            int min_idx = i;
            Tuple t2 = new Tuple();
            t2.setList(copyArray(arr));
            t2.setA(min_idx);
            t2.setB(i);
            resultOfSteps.add(t2);

            for (int j = i + 1; j < n; j++)
            {
                //Frame with all search values
                Tuple t3 = new Tuple();
                t3.setList(copyArray(arr));
                t3.setA(j);
                t3.setB(i);
                if (arr.get(j) < arr.get(min_idx))
                {
                    min_idx = j;
                    t.setA(min_idx);
                }
                resultOfSteps.add(t3);
            }


            // Swap the found minimum element with the first
            // element
            int temp = arr.get(min_idx);
            arr.set(min_idx, arr.get(i));
            arr.set(i, temp);
            resultOfSteps.add(t);
        }

        Tuple finalTuple = new Tuple();
        finalTuple.setList(copyArray(arr));
        finalTuple.setA(n);
        finalTuple.setB(n);
        resultOfSteps.add(finalTuple); //This is the sorted array...

        return resultOfSteps;
    }

    //Merge Sort algorithm adapted from https://www.geeksforgeeks.org/merge-sort/, used https://en.wikipedia.org/wiki/Merge_sort as a resource, too
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    public static ArrayList<MergeSortReturnType> merge(ArrayList<Integer> arr, int l, int m, int r, Color c)
    {
        ArrayList<MergeSortReturnType> returnList = new ArrayList<>();

        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr.get(l + i);
        for (int j = 0; j < n2; ++j)
            R[j] = arr.get(m + 1 + j);


        returnList.add(new MergeSortReturnType(l, r, c, copyArray(arr)));

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr.set(k, L[i]);
                i++;
            } else
            {
                arr.set(k, R[j]);
                j++;
            }
            k++;

            returnList.add(new MergeSortReturnType(l, r, c, copyArray(arr)));
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr.set(k, L[i]);
            i++;
            k++;
            returnList.add(new MergeSortReturnType(l, r, c, copyArray(arr)));
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr.set(k, R[j]);
            j++;
            k++;
            returnList.add(new MergeSortReturnType(l, r, c, copyArray(arr)));
        }

        return returnList;
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public static ArrayList<MergeSortReturnType> mergeSort(ArrayList<Integer> arr, int l, int r)
    {
        ArrayList<MergeSortReturnType> returnList = new ArrayList<>();

        if (l < r)
        {
            Color c = Color.randomColor();
            returnList.add(new MergeSortReturnType(l, r, c, copyArray(arr)));

            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            returnList.addAll(mergeSort(arr, l, m));
            returnList.addAll(mergeSort(arr, m + 1, r));

            // Merge the sorted halves
            returnList.addAll(merge(arr, l, m, r, c));
            returnList.add(new MergeSortReturnType(l, r, c, copyArray(arr)));
        } else
        {
            //Add the case where each element is a singleton!
            Color c = Color.getFound(); //TODO (Sarah): determine if I want to use a consistent color for the base...
            returnList.add(new MergeSortReturnType(l, r, c, copyArray(arr)));
        }

        return returnList;
    }


    //Quicksort algorithm adapted from https://www.geeksforgeeks.org/quick-sort/
    /* This function takes last element as pivot,
      places the pivot element at its correct
      position in sorted array, and places all
      smaller (smaller than pivot) to left of
      pivot and all greater elements to right
      of pivot */
    private static PairOfTuple partition(ArrayList<Integer> arr, int low, int high)
    {
        PairOfTuple pairOfTuple = new PairOfTuple();
        pairOfTuple.addTuple(new Tuple(copyArray(arr), low, high, high));

        int pivot = arr.get(high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++)
        {
            pairOfTuple.addTuple(new Tuple(copyArray(arr), low, high, high));
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j) <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);

                pairOfTuple.addTuple(new Tuple(copyArray(arr), low, high, high));
            }
        }

        pairOfTuple.addTuple(new Tuple(copyArray(arr), low, high, high));
        pairOfTuple.setPi(i + 1);

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        pairOfTuple.addTuple(new Tuple(copyArray(arr), low, high, high));

        return pairOfTuple;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    public static ArrayList<Tuple> quicksort(ArrayList<Integer> arr, int low, int high)
    {
        ArrayList<Tuple> tuples = new ArrayList<>();

        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            PairOfTuple pairOfTuplePartition = partition(arr, low, high);
            tuples.addAll(pairOfTuplePartition.getTuples());

            int pi = pairOfTuplePartition.getPi();

            // Recursively mergeSort elements before
            // partition and after partition
            tuples.addAll(quicksort(arr, low, pi - 1));
            tuples.addAll(quicksort(arr, pi + 1, high));
        }

        Tuple tuple = new Tuple(copyArray(arr), low, high, -1); //the array is sorted, now and so all squares should light up!
        tuples.add(tuple);

        return tuples;
    }
}
