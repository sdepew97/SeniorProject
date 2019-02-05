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
    public static ArrayList<ArrayList<Integer>> bubbleSort(ArrayList<Integer> arr) {
        ArrayList<ArrayList<Integer>> resultsOfSteps = new ArrayList<>();

        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    // swap arr[j+1] and arr[i]
                    int temp = arr.get(j);
                    arr.set(j, arr.get(j + 1));
                    arr.set(j + 1, temp);
                    resultsOfSteps.add(copyArray(arr));
                }
            }
        }

        resultsOfSteps.add(arr);
        return resultsOfSteps;
    }
}
