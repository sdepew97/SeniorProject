package com.example.apphomepages;

import com.example.apphomepages.DynamicProgramming.HelperMethods.DynamicProgrammingHelperMethods;

import org.junit.Assert;
import org.junit.Test;

public class DynamicProgrammingHelperMethodsTest
{
    @Test
    public void copy2DArray()
    {
        int[][] arrayToCopy = {{1, 2}, {3, 4}};
        int[][] copyOfArray = DynamicProgrammingHelperMethods.copy2DArray(arrayToCopy, 2, 2);

        for (int i = 0; i < arrayToCopy.length; i++)
        {
            for (int j = 0; j < arrayToCopy[i].length; j++)
            {
                Assert.assertEquals(arrayToCopy[i][j], copyOfArray[i][j]);
            }
        }
    }
}