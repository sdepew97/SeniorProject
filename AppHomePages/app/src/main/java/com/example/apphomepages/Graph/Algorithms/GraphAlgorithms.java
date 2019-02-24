package com.example.apphomepages.Graph.Algorithms;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphAlgorithms
{
    //Used https://www.geeksforgeeks.org/iterative-depth-first-traversal/ for the algorithm and Dianna Xu's CS330 slides, as well as https://en.wikipedia.org/wiki/Depth-first_search
    public static ArrayList<Integer> depthFirstSearch(Graph g, Integer valueSought, boolean traversal)
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

            if (n.getNodeValue() == valueSought && !traversal)
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

    //Used https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
    public static ArrayList<Integer> breadthFirstSearch(Graph g, Integer valueSought, boolean traversal)
    {
        ArrayList<Integer> visitOrder = new ArrayList<>();
        //set mark to all false
        ArrayList<Node> vertices = g.getGraphElements();

        for (Node n : vertices)
        {
            n.setVisited(false);
        }

        Queue<Node> queue = new LinkedList<>();

        vertices.get(0).mark();
        queue.add(vertices.get(0));

        while (queue.size() != 0)
        {
            Node n = queue.poll();
            visitOrder.add(n.getNodeValue());

            if (n.getNodeValue() == valueSought && !traversal)
            {
                break;
            }

            ArrayList<Node> adjacent = n.getAdjacentNodes();
            for (Node a : adjacent)
            {
                if (!a.isVisited())
                {
                    a.mark();
                    queue.add(a);
                }
            }
        }

        return visitOrder;
    }
}

