package com.example.apphomepages.General.DataTypes;

public class NQueensReturnType
{
    private boolean[][] board;
    private boolean checkingIfSafe;
    private int rowBeingScanned;
    private int columnBeingScanned;

    public NQueensReturnType(boolean[][] board, boolean checkingIfSafe, int rowBeingScanned, int columnBeingScanned)
    {
        this.board = board;
        this.checkingIfSafe = checkingIfSafe;
        this.rowBeingScanned = rowBeingScanned;
        this.columnBeingScanned = columnBeingScanned;
    }

    public boolean[][] getBoard()
    {
        return board;
    }

    public boolean isCheckingIfSafe()
    {
        return checkingIfSafe;
    }

    public int getRowBeingScanned()
    {
        return rowBeingScanned;
    }

    public int getColumnBeingScanned()
    {
        return columnBeingScanned;
    }
}
