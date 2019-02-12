package com.example.apphomepages.Datatypes;

import java.util.ArrayList;

public class PairOfPair
{
    private ArrayList<Pair> pairs;
    private int pi;

    public PairOfPair()
    {
        this.pairs = new ArrayList<>();
        this.pi = -1;
    }

    public void addPair(Pair pair)
    {
        this.pairs.add(pair);
    }

    public void setPi(int pi)
    {
        this.pi = pi;
    }

    public ArrayList<Pair> getPairs()
    {
        return pairs;
    }

    public int getPi()
    {
        return pi;
    }
}
