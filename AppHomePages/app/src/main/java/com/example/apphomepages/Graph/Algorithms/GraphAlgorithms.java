package com.example.apphomepages.Graph.Algorithms;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.DataTypes.TopologicalOrderingReturnType;
import com.example.apphomepages.Graph.HelperMethods.GraphHelperMethods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class GraphAlgorithms
{
    //DFS is used on a graph that is connected. This algorithm will not work on a graph that has multiple disjoint parts.
    //Used https://www.geeksforgeeks.org/iterative-depth-first-traversal/ for the algorithm and Dianna Xu's CS330 slides, as well as https://en.wikipedia.org/wiki/Depth-first_search
    public static ArrayList<Node<Integer>> depthFirstSearch(Graph<Integer> g, Node<Integer> nodeSought, boolean traversal)
    {
        ArrayList<Node<Integer>> visitOrder = new ArrayList<>(); //order of node ids

        //set mark to all false
        ArrayList<Node<Integer>> vertices = g.getGraphElements();

        for (Node<Integer> n : vertices)
        {
            n.setVisited(false);
        }

        Stack<Node<Integer>> stack = new Stack<>();

        //Put a source node
        stack.push(g.getGraphElements().get(0));

        while (!stack.empty())
        {
            Node<Integer> n = stack.peek();
            stack.pop();

            if (!n.isVisited())
            {
                visitOrder.add(n);
                n.mark();
            }

            if (!traversal && n.getNodeId().equals(nodeSought.getNodeId()))
            {
                break;
            }

            ArrayList<Node<Integer>> adjacent = n.getAdjacentNodes();
            for (int i = adjacent.size() - 1; i >= 0; i--)
            {
                Node<Integer> node = adjacent.get(i);
                if (!node.isVisited())
                {
                    stack.push(node);
                }
            }
        }

        return visitOrder;
    }

    //Used https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/
    public static <A> ArrayList<Node<A>> breadthFirstSearch(Graph<A> g, Node<A> nodeSought, boolean traversal)
    {
        ArrayList<Node<A>> visitOrder = new ArrayList<>();

        //set mark to all false
        ArrayList<Node<A>> vertices = g.getGraphElements();

        for (Node<A> n : vertices)
        {
            n.setVisited(false);
        }

        Queue<Node<A>> queue = new LinkedList<>();

        //Start with the root(s) of the graph (nodes that have no elements going into them...this is needed for Topological Ordering)
        //TODO: talk with Richard about this oddness
        ArrayList<Node<A>> isolated = GraphHelperMethods.getIsolatedOrRoot(vertices);

        //Mark all of them!
        for (Node<A> n : isolated)
        {
            n.mark();
            queue.add(n);
        }

        while (queue.size() != 0)
        {
            Node<A> n = queue.poll();
            visitOrder.add(n);

            if (!traversal && n.getNodeId().equals(nodeSought.getNodeId()))
            {
                break;
            }

            ArrayList<Node<A>> adjacent = n.getAdjacentNodes();
            for (Node<A> a : adjacent)
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

    //Used https://www.geeksforgeeks.org/topological-sorting/ and https://www.geeksforgeeks.org/topological-sorting-indegree-based-solution/
    public static TopologicalOrderingReturnType topologicalOrdering(Graph<String> g) throws RuntimeException
    {
        TopologicalOrderingReturnType topologicalOrderingReturnType = new TopologicalOrderingReturnType();
        ArrayList<Integer> visitOrder = new ArrayList<>();

        //set mark to all false
        ArrayList<Node<String>> vertices = g.getGraphElements();

        for (Node<String> n : vertices)
        {
            n.setVisited(false);
        }

        //Compute in degree
        HashMap<Node<String>, Integer> nodesToDegrees = GraphHelperMethods.computeDegree(vertices);
        Queue<Node<String>> queue = new LinkedList<>();

        //Start with the root(s) of the graph (nodes that have no elements going into them...)
        ArrayList<Node<String>> isolated = GraphHelperMethods.getIsolatedOrRoot(vertices);

        //Mark all of them!
        queue.addAll(isolated);

        int visitedNodes = 0;

        while (queue.size() != 0)
        {
            Node<String> node = queue.poll();
            //Add to the visit order and add a copy of the graph to the return type
            visitOrder.add(node.getNodeId());
            topologicalOrderingReturnType.addGraph(GraphHelperMethods.copyGraph(g));

            visitedNodes++;

            for (Node<String> a : node.getAdjacentNodes())
            {
                Integer currentCount = nodesToDegrees.get(a);
                if (currentCount != null)
                {
                    int updatedCount = currentCount - 1;
                    nodesToDegrees.put(a, updatedCount);

                    if (updatedCount == 0)
                    {
                        queue.add(a);
                    }
                }
            }

            //trim node out of graph...(only one way check, since DAG was input)
            g = GraphHelperMethods.trimNode(g, node);
        }

        //error if count is not number of nodes in the graph
        if (visitedNodes != vertices.size())
        {
            //This is an error
            throw new RuntimeException();
        }

        topologicalOrderingReturnType.setVisitOrder(visitOrder);

        return topologicalOrderingReturnType;
    }
}

