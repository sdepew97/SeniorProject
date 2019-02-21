package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class Node
{
    private int nodeValue;
    private ArrayList<Node> adjacentNodes;
    private boolean visited;

    public Node(int nodeValue)
    {
        this.nodeValue = nodeValue;
        this.adjacentNodes = new ArrayList<>();
        this.visited = false;
    }

    public Node(int nodeValue, ArrayList<Node> adjacentNodes)
    {
        this.nodeValue = nodeValue;
        this.adjacentNodes = adjacentNodes;
        this.visited = false;
    }

    public int getNodeValue()
    {
        return nodeValue;
    }

    public ArrayList<Node> getAdjacentNodes()
    {
        return adjacentNodes;
    }

    public void setAdjacentNodes(ArrayList<Node> adjacentNodes)
    {
        this.adjacentNodes = adjacentNodes;
    }

    public void addAdjacentNode(Node adjacentNode)
    {
        this.adjacentNodes.add(adjacentNode);
    }

    public boolean isVisited()
    {
        return visited;
    }

    public void mark()
    {
        this.visited = true;
    }

    public void setVisited(boolean b)
    {
        this.visited = b;
    }
}
