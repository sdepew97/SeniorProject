package com.example.apphomepages.General.DataTypes;

import java.util.ArrayList;

public class Node<A>
{
    private A nodeValue; //allows for a general value to be inserted into nodes, so nodes of type in or String can be created for example
    private ArrayList<Node<A>> adjacentNodes;
    private boolean visited;

    public Node(A nodeValue)
    {
        this.nodeValue = nodeValue;
        this.adjacentNodes = new ArrayList<>();
        this.visited = false;
    }

    public A getNodeValue()
    {
        return nodeValue;
    }

    public ArrayList<Node<A>> getAdjacentNodes()
    {
        return adjacentNodes;
    }

    public void addAdjacentNode(Node<A> adjacentNode)
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
