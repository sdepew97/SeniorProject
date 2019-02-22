package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.Helpers.HelperMethods;
import com.example.apphomepages.Graph.Algorithms.GraphAlgorithms;

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

        ArrayList<Node> nodes = new ArrayList<>();

        for (Integer i : graphValues)
        {
            nodes.add(new Node(i));
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

        Graph g = new Graph(nodes);

        ArrayList<Integer> result = GraphAlgorithms.depthFirstSearch(g, -1);

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ArrayList<Integer> expected = HelperMethods.convertFromArray(nums);

        for (int i = 0; i < nums.length; i++)
            assertEquals(expected.get(i), result.get(i));
    }

    @Test
    public void depthFirstSearch()
    {
        Integer graphValues[] = {1, 2, 7, 8, 3, 6, 9, 12, 4, 5, 10, 11};
        //construct nodes with no connections

        ArrayList<Node> nodes = new ArrayList<>();

        for (Integer i : graphValues)
        {
            nodes.add(new Node(i));
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

        Graph g = new Graph(nodes);

        ArrayList<Integer> result = GraphAlgorithms.depthFirstSearch(g, 8);

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> expected = HelperMethods.convertFromArray(nums);

        for (int i = 0; i < nums.length; i++)
            assertEquals(expected.get(i), result.get(i));
    }

    @Test
    public void breadthFirstTraversal()
    {
        Integer graphValues[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        //construct nodes with no connections

        ArrayList<Node> nodes = new ArrayList<>();

        for (Integer i : graphValues)
        {
            nodes.add(new Node(i));
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

        Graph g = new Graph(nodes);

        ArrayList<Integer> result = GraphAlgorithms.breadthFirstSearch(g, -1);

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        ArrayList<Integer> expected = HelperMethods.convertFromArray(nums);

        for (int i = 0; i < nums.length; i++)
            assertEquals(expected.get(i), result.get(i));
    }

    @Test
    public void breadthFirstSearch()
    {
        Integer graphValues[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        //construct nodes with no connections

        ArrayList<Node> nodes = new ArrayList<>();

        for (Integer i : graphValues)
        {
            nodes.add(new Node(i));
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

        Graph g = new Graph(nodes);

        ArrayList<Integer> result = GraphAlgorithms.breadthFirstSearch(g, 8);

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8};
        ArrayList<Integer> expected = HelperMethods.convertFromArray(nums);

        for (int i = 0; i < nums.length; i++)
            assertEquals(expected.get(i), result.get(i));
    }
}