package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class Graph
{
    ArrayList<Node> graphElements;

    public Graph()
    {
        this.graphElements = new ArrayList<>();
    }

    public Graph(ArrayList<Node> graphElements)
    {
        this.graphElements = graphElements;
    }

    public void addGraphElement(Node node)
    {
        this.graphElements.add(node);
    }

    public ArrayList<Node> getGraphElements()
    {
        return graphElements;
    }

    public int numNodes()
    {
        return this.getGraphElements().size();
    }

    public static void printGraph(Graph g)
    {
        for (Node n : g.getGraphElements())
        {
            System.out.println("\n" + n.getNodeValue() + " connected to:");

            //Print connections
            for (Node node : n.getAdjacentNodes())
            {
                System.out.println("\t\t" + node.getNodeValue());
            }
            System.out.println();
        }
    }
}
