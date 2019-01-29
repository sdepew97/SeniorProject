package com.example.apphomepages;

public class SearchingAlgorithms {

    public SearchingAlgorithms() {
        //Default constructor
    }

    public static int linearSearch(String[] numbers, String target) {
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i].equals(target)) {
                return i;
            }
        }

        return -1;
    }
}
