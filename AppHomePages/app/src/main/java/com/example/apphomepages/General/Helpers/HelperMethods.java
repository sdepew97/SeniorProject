package com.example.apphomepages.General.Helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelperMethods
{
    //Method that allows you to generate an array using the random input, the bound on elements, and the size of the size input
    public static ArrayList<Integer> generateRandomArray(Random r, int size) throws IndexOutOfBoundsException
    {
        if (size < 1)
        {
            throw new IndexOutOfBoundsException();
        }

        ArrayList<Integer> numbers = new ArrayList<>(size);
        int numElements = size;

        for (int j = 0; j < numElements; j++)
        {
            int randomInt = r.nextInt(100);
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

    //Method for testing against Wikipedia visualizations
    public static ArrayList<Integer> generateSetArray()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(8);
        numbers.add(5);
        numbers.add(2);
        numbers.add(6);
        numbers.add(9);
        numbers.add(3);
        numbers.add(1);
        numbers.add(4);
        numbers.add(0);
        numbers.add(7);
        return numbers;
    }

    //Convert an array list of type A to an array list of strings
    public static <A> ArrayList<String> convertToStrings(List<A> arrayList)
    {
        ArrayList<String> numbersStrings = new ArrayList<>();
        int numElements = arrayList.size();

        for (int k = 0; k < numElements; k++)
        {
            numbersStrings.add(arrayList.get(k).toString());
        }

        return numbersStrings;
    }

    public static Integer[] convertToArray(ArrayList<Integer> arrayList)
    {
        Integer[] aArray = new Integer[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++)
        {
            aArray[i] = arrayList.get(i);
        }

        return aArray;
    }

    public static <A> ArrayList<A> convertFromArray(A[] array)
    {
        ArrayList<A> aArray = new ArrayList<>();

        for (int i = 0; i < array.length; i++)
        {
            aArray.add(array[i]);
        }

        return aArray;
    }

}
