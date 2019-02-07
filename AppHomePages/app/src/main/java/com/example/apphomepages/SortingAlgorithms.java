package com.example.apphomepages;

import java.util.ArrayList;

class SortingAlgorithms {

    //Default constructor
    public SortingAlgorithms() {
        //Do nothing
    }

    public static ArrayList<Integer> copyArray(ArrayList<Integer> arrayToCopy) {
        ArrayList<Integer> arrayWithResult = new ArrayList<>();

        for (Integer i : arrayToCopy) {
            arrayWithResult.add(i);
        }

        return arrayWithResult;
    }

    //Bubble sort adapted from https://www.geeksforgeeks.org/bubble-sort/
    public static ArrayList<Tuple> bubbleSort(ArrayList<Integer> arr) {
        ArrayList<Tuple> resultsOfSteps = new ArrayList<>();

        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    Tuple t1 = new Tuple();
                    Tuple t2 = new Tuple();
                    // swap arr[j+1] and arr[j]
                    t1.setSwap1(j+1);
                    t2.setSwap1(j+1);
                    t1.setSwap2(j);
                    t2.setSwap2(j);
                    t1.setList(copyArray(arr));
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    t2.setList(copyArray(arr));
                    resultsOfSteps.add(t1);
                    resultsOfSteps.add(t2);
                }
            }
        }

        resultsOfSteps.add(new Tuple(arr));
        resultsOfSteps.add(new Tuple(arr));
        return resultsOfSteps;
    }

    //Selection sort adapted from

    public static ArrayList<Pair> selectionSort(ArrayList<Integer> arr) {
        ArrayList<Pair> resultOfSteps = new ArrayList<>();

        int n = arr.size();

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            Pair p = new Pair();
            p.setList(copyArray(arr));
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(min_idx)) {
                    min_idx = j;
                    p.setMinimum(min_idx);
                }
            }


            // Swap the found minimum element with the first
            // element
            int temp = arr.get(min_idx);
            arr.set(min_idx, arr.get(i));
            arr.set(i, temp);
            resultOfSteps.add(p);
        }

        Pair finalPair = new Pair();
        finalPair.setList(copyArray(arr));
        finalPair.setMinimum(n);
        resultOfSteps.add(finalPair); //This is the sorted array...


        return resultOfSteps;
    }
}
