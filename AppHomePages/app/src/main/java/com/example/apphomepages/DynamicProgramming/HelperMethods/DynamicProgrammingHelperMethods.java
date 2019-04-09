package com.example.apphomepages.DynamicProgramming.HelperMethods;

public class DynamicProgrammingHelperMethods
{
    //The input to this method must be an x by y array
    public static int[][] copy2DArray(int[][] source, int x, int y)
    {
        int[][] copy = new int[x][y]; //the input must be an x by y array

        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                copy[i][j] = source[i][j];
            }
        }

        return copy;
    }
}
