package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class Graph<A>
{
    ArrayList<Node<A>> graphElements;

    public Graph()
    {
        this.graphElements = new ArrayList<>();
    }

    public Graph(ArrayList<Node<A>> graphElements)
    {
        this.graphElements = graphElements;
    }

    public ArrayList<Node<A>> getGraphElements()
    {
        return graphElements;
    }

    public int numNodes()
    {
        return this.getGraphElements().size();
    }
}
