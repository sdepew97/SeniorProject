package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class PairOfTuple
{
    private ArrayList<Tuple> tuples;
    private int pi;


    public PairOfTuple()
    {
        this.tuples = new ArrayList<>();
        this.pi = -1;
    }

    public void addTuple(Tuple tuple)
    {
        this.tuples.add(tuple);
    }

    public ArrayList<Tuple> getTuples()
    {
        return tuples;
    }

    public int getPi()
    {
        return pi;
    }

    public void setPi(int pi)
    {
        this.pi = pi;
    }
}
