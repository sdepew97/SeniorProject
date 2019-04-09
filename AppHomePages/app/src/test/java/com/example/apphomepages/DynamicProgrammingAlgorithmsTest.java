package com.example.apphomepages;

import com.example.apphomepages.DynamicProgramming.Algorithms.DynamicProgrammingAlgorithms;
import com.example.apphomepages.General.DataTypes.MinEditReturnType;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class DynamicProgrammingAlgorithmsTest
{
    @Test
    //Used http://www.let.rug.nl/kleiweg/lev/ to check answer and for values to put into the expectedBoard 2-d array
    public void minEditDist()
    {
        ArrayList<MinEditReturnType> algorithmOutput = DynamicProgrammingAlgorithms.minEditDist("cart", "march");
        int[][] boardResult = algorithmOutput.get(algorithmOutput.size() - 1).getBoard();
        int[][] expectedBoard = {
                {0, 1, 2, 3, 4, 5},
                {1, 2, 3, 4, 3, 4},
                {2, 3, 2, 3, 4, 5},
                {3, 4, 3, 2, 3, 4},
                {4, 5, 4, 3, 4, 5}
        };

        Assert.assertEquals(5, boardResult.length);
        Assert.assertEquals(6, boardResult[0].length);

        Assert.assertEquals(expectedBoard.length, boardResult.length);

        for (int i = 0; i < boardResult.length; i++)
        {
            Assert.assertEquals(expectedBoard[i].length, boardResult[i].length);
            for (int j = 0; j < boardResult[i].length; j++)
            {
                Assert.assertEquals(expectedBoard[i][j], boardResult[i][j]);
            }
        }
    }
}