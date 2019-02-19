package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class Pair
{
    private ArrayList<Integer> list;
    private Integer sought;

    public Pair()
    {
        this.list = new ArrayList<>();
        sought = -1; //Default sought is NOT an entry into the array
    }

    public Pair(ArrayList<Integer> list, Integer minimum)
    {
        this.list = list;
        this.sought = minimum;
    }

    public ArrayList<Integer> getList()
    {
        return list;
    }

    public void setList(ArrayList<Integer> list)
    {
        this.list = list;
    }

    public Integer getSought()
    {
        return sought;
    }

    public void setSought(Integer sought)
    {
        this.sought = sought;
    }

    public ArrayList<Integer> constructList()
    {
        ArrayList<Integer> returnVal = new ArrayList<>();

        returnVal.add(sought);

        return returnVal;
    }
}
