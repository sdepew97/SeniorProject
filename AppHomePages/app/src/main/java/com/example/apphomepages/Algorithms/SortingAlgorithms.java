package com.example.apphomepages.Algorithms;

import com.example.apphomepages.Datatypes.Tuple;

import java.util.ArrayList;

public class SortingAlgorithms
{
    //Default constructor
    public SortingAlgorithms()
    {
        //Do nothing
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

    //Bubble sort adapted from https://www.geeksforgeeks.org/bubble-sort/
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

    //Insertion sort adapted from https://www.geeksforgeeks.org/insertion-sort/
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

    //Selection sort adapted from https://www.geeksforgeeks.org/selection-sort/
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

    //Merge Sort algorithm adapted from https://www.geeksforgeeks.org/merge-sort/
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    public static void mergeSort(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            } else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    public static void sort(int arr[], int l, int r)
    {
        if (l < r)
        {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            mergeSort(arr, l, m, r);
        }
    }
}
