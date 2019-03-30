package com.example.apphomepages.SearchAndSort.Algorithms;

import com.example.apphomepages.General.DataTypes.MergeSortReturnType;
import com.example.apphomepages.General.DataTypes.Tuple;

import java.util.ArrayList;

public class SortingAlgorithms
{
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

    //Copies an array list from left to right inclusive of right
    public static ArrayList<Integer> copyArray(ArrayList<Integer> arrayToCopy, int left, int right)
    {
        ArrayList<Integer> arrayWithResult = new ArrayList<>();

        for (int i = left; i <= right; i++)
        {
            arrayWithResult.add(arrayToCopy.get(i));
        }

        return arrayWithResult;
    }

    //Copies an array list from left to right inclusive of right
    public static ArrayList<String> copyArrayString(ArrayList<Integer> arrayToCopy, int left, int right)
    {
        ArrayList<String> arrayWithResult = new ArrayList<>();

        for (int i = left; i <= right; i++)
        {
            arrayWithResult.add(arrayToCopy.get(i).toString());
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
    public static ArrayList<MergeSortReturnType> merge(ArrayList<Integer> arr, int l, int m, int r)
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

            returnList.add(new MergeSortReturnType(l, r, copyArray(arr), true));
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1)
        {
            arr.set(k, L[i]);
            i++;
            k++;
            returnList.add(new MergeSortReturnType(l, r, copyArray(arr), true));
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2)
        {
            arr.set(k, R[j]);
            j++;
            k++;
            returnList.add(new MergeSortReturnType(l, r, copyArray(arr), true));
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
            returnList.add(new MergeSortReturnType(l, r, copyArray(arr), false));

            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            returnList.addAll(mergeSort(arr, l, m));
            returnList.addAll(mergeSort(arr, m + 1, r));

            // Merge the sorted halves
            returnList.addAll(merge(arr, l, m, r));
            returnList.add(new MergeSortReturnType(l, r, copyArray(arr), false));
        } else
        {
            //Add the case where each element is a singleton!
            returnList.add(new MergeSortReturnType(l, r, copyArray(arr), false));
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
    private static int partition(ArrayList<Integer> arr, int low, int high, ArrayList<Tuple> accumulator)
    {
        accumulator.add(new Tuple(copyArray(arr), low, high, high, -1, false));

        int pivot = arr.get(high);
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++)
        {
            accumulator.add(new Tuple(copyArray(arr), low, high, high, j, true));
            // If current element is smaller than or
            // equal to pivot
            if (arr.get(j) <= pivot)
            {
                i++;

                // swap arr[i] and arr[j]
                int temp = arr.get(i);
                arr.set(i, arr.get(j));
                arr.set(j, temp);

                accumulator.add(new Tuple(copyArray(arr), low, high, high, j, true));
            }
        }

        accumulator.add(new Tuple(copyArray(arr), low, high, high, -1, false));

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr.get(i + 1);
        arr.set(i + 1, arr.get(high));
        arr.set(high, temp);

        accumulator.add(new Tuple(copyArray(arr), low, high, i + 1, -1, false));

        return i + 1;
    }


    /* The main function that implements QuickSort()
      arr[] --> Array to be sorted,
      low  --> Starting index,
      high  --> Ending index */
    public static void quicksort(ArrayList<Tuple> accumulator, ArrayList<Integer> arr, int low, int high)
    {
        int pi;

        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            pi = partition(arr, low, high, accumulator);

            // Recursively mergeSort elements before
            // partition and after partition
            quicksort(accumulator, arr, low, pi - 1);
            quicksort(accumulator, arr, pi + 1, high);
        }
    }
}
