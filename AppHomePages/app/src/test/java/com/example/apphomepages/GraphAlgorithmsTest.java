package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.Helpers.HelperMethods;
import com.example.apphomepages.Graph.Algorithms.GraphAlgorithms;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GraphAlgorithmsTest
{
    //Used Dianna Xu's Algorithm's 330 slides for reference when developing these tests
    @Test
    public void depthFirstTraversal()
    {
        Integer graphValues[] = {1, 2, 7, 8, 3, 6, 9, 12, 4, 5, 10, 11};
        //construct nodes with no connections

        ArrayList<Node<Integer>> nodes = new ArrayList<>();

        int nodeId = 0;
        for (Integer i : graphValues)
        {
            nodes.add(new Node<>(i, nodeId));
            nodeId++;
        }

        //put in all desired connections
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(3));
        nodes.get(3).addAdjacentNode(nodes.get(0));

        nodes.get(1).addAdjacentNode(nodes.get(4));
        nodes.get(4).addAdjacentNode(nodes.get(1));

        nodes.get(1).addAdjacentNode(nodes.get(5));
        nodes.get(5).addAdjacentNode(nodes.get(1));

        nodes.get(3).addAdjacentNode(nodes.get(6));
        nodes.get(6).addAdjacentNode(nodes.get(3));

        nodes.get(3).addAdjacentNode(nodes.get(7));
        nodes.get(7).addAdjacentNode(nodes.get(3));

        nodes.get(4).addAdjacentNode(nodes.get(8));
        nodes.get(8).addAdjacentNode(nodes.get(4));

        nodes.get(4).addAdjacentNode(nodes.get(9));
        nodes.get(9).addAdjacentNode(nodes.get(4));

        nodes.get(6).addAdjacentNode(nodes.get(10));
        nodes.get(10).addAdjacentNode(nodes.get(6));

        nodes.get(6).addAdjacentNode(nodes.get(11));
        nodes.get(11).addAdjacentNode(nodes.get(6));

        Graph<Integer> g = new Graph<>(nodes);

        ArrayList<Node<Integer>> result = GraphAlgorithms.depthFirstSearch(g, null, true);

        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ArrayList<Integer> expected = HelperMethods.convertFromArray(nums);

        for (int i = 0; i < nums.length; i++)
            assertEquals(expected.get(i), result.get(i).getNodeValue());

        Assert.assertEquals(nums.length, result.size());
    }

    @Test
    public void depthFirstSearch()
    {
        Integer graphValues[] = {1, 2, 7, 8, 3, 6, 9, 12, 4, 5, 10, 11};
        //construct nodes with no connections

        ArrayList<Node<Integer>> nodes = new ArrayList<>();

        int nodeId = 0;
        for (Integer i : graphValues)
        {
            nodes.add(new Node<>(i, nodeId));
            nodeId++;
        }

        //put in all desired connections
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(3));
        nodes.get(3).addAdjacentNode(nodes.get(0));

        nodes.get(1).addAdjacentNode(nodes.get(4));
        nodes.get(4).addAdjacentNode(nodes.get(1));

        nodes.get(1).addAdjacentNode(nodes.get(5));
        nodes.get(5).addAdjacentNode(nodes.get(1));

        nodes.get(3).addAdjacentNode(nodes.get(6));
        nodes.get(6).addAdjacentNode(nodes.get(3));

        nodes.get(3).addAdjacentNode(nodes.get(7));
        nodes.get(7).addAdjacentNode(nodes.get(3));

        nodes.get(4).addAdjacentNode(nodes.get(8));
        nodes.get(8).addAdjacentNode(nodes.get(4));

        nodes.get(4).addAdjacentNode(nodes.get(9));
        nodes.get(9).addAdjacentNode(nodes.get(4));

        nodes.get(6).addAdjacentNode(nodes.get(10));
        nodes.get(10).addAdjacentNode(nodes.get(6));

        nodes.get(6).addAdjacentNode(nodes.get(11));
        nodes.get(11).addAdjacentNode(nodes.get(6));

        Graph<Integer> g = new Graph<>(nodes);

        ArrayList<Node<Integer>> result = GraphAlgorithms.depthFirstSearch(g, nodes.get(3), false);

        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> expected = HelperMethods.convertFromArray(nums);

        for (int i = 0; i < nums.length; i++)
            assertEquals(expected.get(i), result.get(i).getNodeValue());

        Assert.assertEquals(nums.length, result.size());
    }

    @Test
    public void breadthFirstTraversal()
    {
        Integer graphValues[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        //construct nodes with no connections

        ArrayList<Node<Integer>> nodes = new ArrayList<>();

        int nodeId = 0;
        for (Integer i : graphValues)
        {
            nodes.add(new Node<>(i, nodeId));
            nodeId++;
        }

        //put in all desired connections
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(3));
        nodes.get(3).addAdjacentNode(nodes.get(0));

        nodes.get(1).addAdjacentNode(nodes.get(4));
        nodes.get(4).addAdjacentNode(nodes.get(1));

        nodes.get(1).addAdjacentNode(nodes.get(5));
        nodes.get(5).addAdjacentNode(nodes.get(1));

        nodes.get(3).addAdjacentNode(nodes.get(6));
        nodes.get(6).addAdjacentNode(nodes.get(3));

        nodes.get(3).addAdjacentNode(nodes.get(7));
        nodes.get(7).addAdjacentNode(nodes.get(3));

        nodes.get(4).addAdjacentNode(nodes.get(8));
        nodes.get(8).addAdjacentNode(nodes.get(4));

        nodes.get(4).addAdjacentNode(nodes.get(9));
        nodes.get(9).addAdjacentNode(nodes.get(4));

        nodes.get(6).addAdjacentNode(nodes.get(10));
        nodes.get(10).addAdjacentNode(nodes.get(6));

        nodes.get(6).addAdjacentNode(nodes.get(11));
        nodes.get(11).addAdjacentNode(nodes.get(6));

        Graph<Integer> g = new Graph<>(nodes);

        ArrayList<Node<Integer>> result = GraphAlgorithms.breadthFirstSearch(g, null, true);

        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ArrayList<Integer> expected = HelperMethods.convertFromArray(nums);

        for (int i = 0; i < nums.length; i++)
            assertEquals(expected.get(i), result.get(i).getNodeValue());

        Assert.assertEquals(nums.length, result.size());
    }

    @Test
    public void breadthFirstSearch()
    {
        Integer graphValues[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        //construct nodes with no connections

        ArrayList<Node<Integer>> nodes = new ArrayList<>();

        int nodeId = 0;
        for (Integer i : graphValues)
        {
            nodes.add(new Node<>(i, nodeId));
            nodeId++;
        }

        //put in all desired connections
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        nodes.get(0).addAdjacentNode(nodes.get(3));
        nodes.get(3).addAdjacentNode(nodes.get(0));

        nodes.get(1).addAdjacentNode(nodes.get(4));
        nodes.get(4).addAdjacentNode(nodes.get(1));

        nodes.get(1).addAdjacentNode(nodes.get(5));
        nodes.get(5).addAdjacentNode(nodes.get(1));

        nodes.get(3).addAdjacentNode(nodes.get(6));
        nodes.get(6).addAdjacentNode(nodes.get(3));

        nodes.get(3).addAdjacentNode(nodes.get(7));
        nodes.get(7).addAdjacentNode(nodes.get(3));

        nodes.get(4).addAdjacentNode(nodes.get(8));
        nodes.get(8).addAdjacentNode(nodes.get(4));

        nodes.get(4).addAdjacentNode(nodes.get(9));
        nodes.get(9).addAdjacentNode(nodes.get(4));

        nodes.get(6).addAdjacentNode(nodes.get(10));
        nodes.get(10).addAdjacentNode(nodes.get(6));

        nodes.get(6).addAdjacentNode(nodes.get(11));
        nodes.get(11).addAdjacentNode(nodes.get(6));

        Graph<Integer> g = new Graph<>(nodes);

        ArrayList<Node<Integer>> result = GraphAlgorithms.breadthFirstSearch(g, nodes.get(7), false);

        Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> expected = HelperMethods.convertFromArray(nums);

        for (int i = 0; i < nums.length; i++)
            assertEquals(expected.get(i), result.get(i).getNodeValue());

        Assert.assertEquals(nums.length, result.size());
    }

    @Test
    public void topologicalOrdering()
    {
        String[] nodeValues = {"Algebra 1", "Geometry", "Algebra 2", "Trigonometry", "Statistics", "Probability", "Pre-Calculus", "Calculus I", "AP Calculus"};
        ArrayList<String> nodeValuesList = HelperMethods.convertFromArray(nodeValues);
        Graph<String> g = createDAG(nodeValuesList);

        ArrayList<String> topologicalOrdering = GraphAlgorithms.topologicalOrdering(g);
        String[] expectedValues = {"Algebra 1", "Statistics", "Probability", "Geometry", "Algebra 2", "Trigonometry", "Pre-Calculus", "Calculus I", "AP Calculus"};

        for (int i = 0; i < nodeValues.length; i++)
        {
            Assert.assertTrue(expectedValues[i].equals(topologicalOrdering.get(i)));
        }
    }

    //A helper method to create a DAG from a string of node values
    private Graph<String> createDAG(ArrayList<String> nodeValuesList)
    {
        //construct nodes with no connections
        ArrayList<Node<String>> nodes = new ArrayList<>();

        int nodeId = 0;
        for (String s : nodeValuesList)
        {
            nodes.add(new Node<>(s, nodeId));
            nodeId++;
        }

        //put in all desired connections to create the graph; note that all connections must be acyclic! (because of topological ordering..
        nodes.get(0).addAdjacentNode(nodes.get(1)); //Algebra 1 --> Geometry
        nodes.get(0).addAdjacentNode(nodes.get(2)); //Algebra 1 --> Algebra 2

        nodes.get(1).addAdjacentNode(nodes.get(2)); //Geometry --> Algebra 2

        //nodes.get(2).addAdjacentNode(nodes.get(1)); //Algebra 2 --> Geometry
        nodes.get(2).addAdjacentNode(nodes.get(3)); //Algebra 2 --> Trigonometry

        nodes.get(3).addAdjacentNode(nodes.get(6)); //Trigonometry --> Pre-Calculus

        nodes.get(4).addAdjacentNode(nodes.get(6)); //Statistics --> Pre-Calculus

        nodes.get(5).addAdjacentNode(nodes.get(6)); //Probability --> Pre-Calculus

        nodes.get(6).addAdjacentNode(nodes.get(7)); //Pre-Calculus --> Calculus I
        nodes.get(6).addAdjacentNode(nodes.get(8)); //Pre-Calculus --> AP Calculus

        //return the graph
        return new Graph<>(nodes);
    }
}