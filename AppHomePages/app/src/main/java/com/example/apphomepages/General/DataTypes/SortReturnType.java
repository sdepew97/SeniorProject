package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class SortReturnType
{
    private ArrayList<Integer> list;
    private Integer a;
    private Integer b;

    public SortReturnType()
    {
        this.list = new ArrayList<>();
        a = -1;
        b = -1;
    }

    public SortReturnType(ArrayList<Integer> list)
    {
        this.list = list;
        a = -1;
        b = -1;
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

    public ArrayList<Integer> constructPair()
    {
        ArrayList<Integer> returnVal = new ArrayList<>();

        returnVal.add(a);
        returnVal.add(b);

        return returnVal;
    }
}
