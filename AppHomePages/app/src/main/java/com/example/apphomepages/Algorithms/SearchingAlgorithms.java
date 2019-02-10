package com.example.apphomepages.Algorithms;

import java.util.ArrayList;
import java.util.Random;

public class SearchingAlgorithms
{

    public SearchingAlgorithms()
    {
        //Default constructor
    }


    public static int linearSearch(ArrayList<Integer> numbers, Integer target)
    {
        if (target == null)
        {
            return -1;
        }

        for (int i = 0; i < numbers.size(); i++)
        {
            if (numbers.get(i) == target)
            {
                return i;
            }
        }

        return -1;
    }

    //used the algorithm found at this website for an iterative version of binary search https://medium.com/@gustavo.ponce.ch/binary-search-with-java-recursive-iterative-java-collections-5a12a814a27c
    public static ArrayList<Integer> binarySearchWithLocations(ArrayList<Integer> array, Integer search)
    {
        Random r = new Random();
        boolean set = false;
        int randomInt;

        if (search == null)
        {
            do
            {
                randomInt = r.nextInt(10 * 10);
                set = !(array.contains(randomInt));
            } while (!set);

            search = randomInt;
        }

        ArrayList<Integer> squaresToHighlight = new ArrayList<>();

        int start = 0;
        int end = array.size() - 1;

        while (start <= end)
        {

            int middle = (start + end) / 2;
            squaresToHighlight.add(middle);

            if (search < array.get(middle))
            {
                end = middle - 1;
            }

            if (search > array.get(middle))
            {
                start = middle + 1;
            }

            if (search == array.get(middle))
            {
                squaresToHighlight.add(middle);
                return squaresToHighlight;
            }
        }

        return squaresToHighlight;
    }
}
