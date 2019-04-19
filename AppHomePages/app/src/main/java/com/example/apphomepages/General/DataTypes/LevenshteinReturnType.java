package com.example.apphomepages.General.DataTypes;

public class LevenshteinReturnType
{
    private int[][] board;
    private boolean minOperation;
    private int i;
    private int j;

    public LevenshteinReturnType(int[][] board, boolean minOperation, int i, int j)
    {
        this.board = board;
        this.minOperation = minOperation;
        this.i = i;
        this.j = j;
    }

    public int[][] getBoard()
    {
        return board;
    }

    public boolean isMinOperation()
    {
        return minOperation;
    }

    public int getI()
    {
        return i;
    }

    public int getJ()
    {
        return j;
    }
}
