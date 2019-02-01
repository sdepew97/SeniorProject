package com.example.apphomepages;

public class SearchingAlgorithms {

    public SearchingAlgorithms() {
        //Default constructor
    }

    public static int linearSearch(int[] numbers, int target) {
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i] == target) {
                return i;
            }
        }

        return -1;
    }
}
