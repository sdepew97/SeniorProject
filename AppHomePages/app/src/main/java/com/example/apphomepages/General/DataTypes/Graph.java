package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class Graph<A>
{
    private ArrayList<Node<A>> graphElements;

    public Graph(ArrayList<Node<A>> graphElements)
    {
        this.graphElements = graphElements;
    }

    public ArrayList<Node<A>> getGraphElements()
    {
        return graphElements;
    }
}
