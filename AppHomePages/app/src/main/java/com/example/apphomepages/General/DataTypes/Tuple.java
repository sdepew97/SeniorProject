package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class Tuple
{

    private ArrayList<Integer> list;
    private Integer a;
    private Integer b;
    private Integer pivot;

    public Tuple()
    {
        this.list = new ArrayList<>();
        a = -1;
        b = -1;
        pivot = -1;
    }

    public Tuple(ArrayList<Integer> list)
    {
        this.list = list;
        a = -1;
        b = -1;
        pivot = -1;
    }

    public Tuple(ArrayList<Integer> list, Integer a, Integer b, Integer pivot)
    {
        this.list = list;
        this.a = a;
        this.b = b;
        this.pivot = pivot;
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

    public ArrayList<Integer> constructPair()
    {
        ArrayList<Integer> returnVal = new ArrayList<>();

        returnVal.add(a);
        returnVal.add(b);

        return returnVal;
    }
}
