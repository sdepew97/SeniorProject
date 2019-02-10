package com.example.apphomepages.General;

import java.util.ArrayList;
import java.util.Random;

public class HelperMethods
{
    public HelperMethods()
    {
    }

    //Method that allows you to generate an array using the random input, the bound on elements, and the size of the size input
    public static ArrayList<Integer> generateRandomArray(Random r, int size, int bound)
    {
        ArrayList<Integer> numbers = new ArrayList<>(size);
        int numElements = size;

        for (int j = 0; j < numElements; j++)
        {
            int randomInt = r.nextInt(bound * 10);
            if (r.nextBoolean())
            {
                numbers.add(j, -randomInt);
            } else
            {
                numbers.add(j, randomInt);
            }
        }

        return numbers;
    }

    //Convert an array list of type A to an array list of strings
    public static <A> ArrayList<String> convertToStrings(ArrayList<A> arrayList)
    {
        ArrayList<String> numbersStrings = new ArrayList<>();
        int numElements = arrayList.size();

        for (int k = 0; k < numElements; k++)
        {
            numbersStrings.add(arrayList.get(k).toString());
        }

        return numbersStrings;
    }

}