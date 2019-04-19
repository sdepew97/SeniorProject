package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.LevenshteinReturnType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LevenshteinReturnTypeTest
{
    private LevenshteinReturnType levenshteinReturnType;

    @Before
    public void initialize()
    {
        int[][] board = {{1, 2}, {3, 4}};

        levenshteinReturnType = new LevenshteinReturnType(board, true, 0, 0);
    }

    @Test
    public void getBoard()
    {
        int[][] board = levenshteinReturnType.getBoard();

        Assert.assertEquals(1, board[0][0]);
        Assert.assertEquals(2, board[0][1]);
        Assert.assertEquals(3, board[1][0]);
        Assert.assertEquals(4, board[1][1]);
    }

    @Test
    public void isMinOperation()
    {
        Assert.assertTrue(levenshteinReturnType.isMinOperation());
    }

    @Test
    public void getI()
    {
        Assert.assertEquals(0, levenshteinReturnType.getI());
    }

    @Test
    public void getJ()
    {
        Assert.assertEquals(0, levenshteinReturnType.getJ());
    }
}