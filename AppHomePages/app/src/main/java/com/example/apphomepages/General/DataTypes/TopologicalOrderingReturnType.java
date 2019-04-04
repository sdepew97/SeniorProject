package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class TopologicalOrderingReturnType
{
    private ArrayList<Integer> visitOrder;
    private ArrayList<Graph<String>> graphs;

    public TopologicalOrderingReturnType()
    {
        this.visitOrder = new ArrayList<>();
        this.graphs = new ArrayList<>();
    }

    public ArrayList<Integer> getVisitOrder()
    {
        return visitOrder;
    }

    public void setVisitOrder(ArrayList<Integer> visitOrder)
    {
        this.visitOrder = visitOrder;
    }

    public ArrayList<Graph<String>> getGraphs()
    {
        return graphs;
    }

    public void addGraph(Graph<String> g)
    {
        this.graphs.add(g);
    }

}
