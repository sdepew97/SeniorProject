package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class MergeSortReturnType
{
    private int left;
    private int right;
    private ArrayList<Integer> numbers;
    private boolean merging;

    public MergeSortReturnType(int left, int right, ArrayList<Integer> numbers, boolean merging)
    {
        this.left = left;
        this.right = right;
        this.numbers = numbers;
        this.merging = merging;
    }

    public int getLeft()
    {
        return left;
    }

    public int getRight()
    {
        return right;
    }

    public ArrayList<Integer> getNumbers()
    {
        return numbers;
    }

    public boolean isMerging()
    {
        return merging;
    }
}
