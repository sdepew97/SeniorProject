package com.example.apphomepages;

import java.util.ArrayList;

public class SearchingAlgorithms {

    public SearchingAlgorithms() {
        //Default constructor
    }

    public static int linearSearch(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == target) {
                return i;
            }
        }

        return -1;
    }

    //used the algorithm found at this website for an iterative version of binary search https://medium.com/@gustavo.ponce.ch/binary-search-with-java-recursive-iterative-java-collections-5a12a814a27c
    public static ArrayList<Integer> binarySearchWithLocations(int[] array, int search) {
        ArrayList<Integer> squaresToHighlight = new ArrayList<>();

        int start = 0;
        int end = array.length - 1;

        while (start <= end) {

            int middle = (start + end) / 2;
            squaresToHighlight.add(middle);

            if (search < array[middle]) {
                end = middle - 1;
            }

            if (search > array[middle]) {
                start = middle + 1;
            }

            if (search == array[middle]) {
                squaresToHighlight.add(middle);
                return squaresToHighlight;
            }
        }

        return squaresToHighlight;
    }
}
