package com.example.apphomepages.General.DataTypes;

public class MinEditReturnType
{
    private int[][] board;
    private boolean minOperation;
    private int i;
    private int j;
    private String[] valuesToMin;

    public MinEditReturnType(int[][] board, boolean minOperation, int i, int j, String[] valuesToMin)
    {
        this.board = board;
        this.minOperation = minOperation;
        this.i = i;
        this.j = j;
        this.valuesToMin = valuesToMin;
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

    public String[] getValuesToMin()
    {
        return valuesToMin;
    }
}
