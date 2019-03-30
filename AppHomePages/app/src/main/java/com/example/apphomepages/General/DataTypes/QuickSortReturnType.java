package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class QuickSortReturnType
{
    private ArrayList<Integer> list;
    private Integer a;
    private Integer b;
    private Integer pivot;
    private Integer beingViewed;
    private boolean partitioning;

    public QuickSortReturnType(ArrayList<Integer> list, Integer a, Integer b, Integer pivot, Integer beingViewed, boolean partitioning)
    {
        this.list = list;
        this.a = a;
        this.b = b;
        this.pivot = pivot;
        this.beingViewed = beingViewed;
        this.partitioning = partitioning;
    }

    public ArrayList<Integer> getList()
    {
        return list;
    }

    public void setList(ArrayList<Integer> list)
    {
        this.list = list;
    }

    public Integer getA()
    {
        return a;
    }

    public void setA(Integer a)
    {
        this.a = a;
    }

    public Integer getB()
    {
        return b;
    }

    public void setB(Integer b)
    {
        this.b = b;
    }

    public Integer getPivot()
    {
        return pivot;
    }

    public Integer getBeingViewed()
    {
        return beingViewed;
    }

    public boolean isPartitioning()
    {
        return partitioning;
    }
}
