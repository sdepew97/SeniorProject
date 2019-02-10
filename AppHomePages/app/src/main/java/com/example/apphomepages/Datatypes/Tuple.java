package com.example.apphomepages.Datatypes;

import java.util.ArrayList;

public class Tuple
{

    private ArrayList<Integer> list;
    private Integer a;
    private Integer b;

    public Tuple()
    {
        this.list = new ArrayList<>();
        a = -1;
        b = -1;
    }

    public Tuple(ArrayList<Integer> list)
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

    public void setA(Integer a)
    {
        this.a = a;
    }

    public void setB(Integer b)
    {
        this.b = b;
    }

    public Integer getA()
    {
        return a;
    }

    public Integer getB()
    {
        return b;
    }

    public ArrayList<Integer> constructPair()
    {
        ArrayList<Integer> returnVal = new ArrayList<>();

        returnVal.add(a);
        returnVal.add(b);

        return returnVal;
    }
}
