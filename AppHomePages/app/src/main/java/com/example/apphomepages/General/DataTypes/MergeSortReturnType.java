package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class MergeSortReturnType
{
    private int left;
    private int right;
    private Color color;
    private ArrayList<Integer> numbers;

    public MergeSortReturnType(int left, int right, Color color, ArrayList<Integer> numbers)
    {
        this.left = left;
        this.right = right;
        this.color = color;
        this.numbers = numbers;
    }

    public int getLeft()
    {
        return left;
    }

    public int getRight()
    {
        return right;
    }

    public Color getColor()
    {
        return color;
    }

    public ArrayList<Integer> getNumbers()
    {
        return numbers;
    }
}
