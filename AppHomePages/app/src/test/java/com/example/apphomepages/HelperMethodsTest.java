package com.example.apphomepages;

import com.example.apphomepages.General.Helpers.HelperMethods;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelperMethodsTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    //used https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
    public void generateRandomArrayIndexWrong()
    {
        exception.expect(IndexOutOfBoundsException.class);
        ArrayList<Integer> randomArray = HelperMethods.generateRandomArray(new Random(), -10);
    }

    @Test
    public void generateRandomArray()
    {
        ArrayList<Integer> randomArray = HelperMethods.generateRandomArray(new Random(), 10);

        for (Integer i : randomArray)
            assertTrue(-99 <= i && i <= 99);
    }

    @Test
    public void generateSetArray()
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

        ArrayList<Integer> numbersGenerated = HelperMethods.generateSetArray();

        for (Integer i : numbers)
            assertEquals(numbers.get(i), numbersGenerated.get(i));
    }

    @Test
    public void convertToStrings()
    {
        ArrayList<Integer> randomArray = HelperMethods.generateRandomArray(new Random(), 10);
        ArrayList<String> randomArrayStrings = HelperMethods.convertToStrings(randomArray);

        for (int i = 0; i < randomArray.size(); i++)
            assertEquals(randomArray.get(i).toString(), randomArrayStrings.get(i));
    }

    @Test
    public void convertToArray()
    {
        ArrayList<Integer> randomArray = HelperMethods.generateRandomArray(new Random(), 10);
        Integer[] randomArrayAnswer = HelperMethods.convertToArray(randomArray);

        for (int i = 0; i < randomArray.size(); i++)
            assertEquals(randomArray.get(i), randomArrayAnswer[i]);
    }

    @Test
    public void convertFromArray()
    {
        int[] arr = {12, 14, 122, -10};
        ArrayList<Integer> arrayList = HelperMethods.convertFromArray(arr);

        for (int i = 0; i < arr.length; i++)
            assertEquals(new Integer(arr[i]), arrayList.get(i));
    }
}