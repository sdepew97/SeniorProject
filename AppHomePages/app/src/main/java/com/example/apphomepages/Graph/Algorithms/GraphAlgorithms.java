package com.example.apphomepages.Graph.Algorithms;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;

import java.util.ArrayList;
import java.util.Stack;

public class GraphAlgorithms
{
    public static ArrayList<Integer> depthFirstSearch(Graph g, Integer valueSought)
    {
        ArrayList<Integer> visitOrder = new ArrayList<>();
        //set mark to all false
        ArrayList<Node> vertices = g.getGraphElements();

        for (Node n : vertices)
        {
            n.setVisited(false);
        }

        Stack<Node> stack = new Stack<>();
        stack.push(vertices.get(0));

        while (!stack.empty())
        {
            Node n = stack.peek();
            stack.pop();

            if (!n.isVisited())
            {
                visitOrder.add(n.getNodeValue());
                n.mark();
            }

            if (n.getNodeValue() == valueSought)
            {
                break;
            }

            ArrayList<Node> adjacent = n.getAdjacentNodes();
            for (int i = adjacent.size() - 1; i >= 0; i--)
            {
                Node node = adjacent.get(i);
                if (!node.isVisited())
                {
                    stack.push(node);
                }
            }
        }

        return visitOrder;

    }

}

