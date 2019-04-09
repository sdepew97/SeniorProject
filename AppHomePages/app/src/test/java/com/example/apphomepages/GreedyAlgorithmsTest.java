package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.DijkstrasReturnType;
import com.example.apphomepages.Greedy.Algorithms.GreedyAlgorithms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class GreedyAlgorithmsTest
{
    private int[][] graph;

    @Before
    public void initialize()
    {
        graph = new int[][]{
                {0, 7, 9, 0, 0, 14},
                {7, 0, 10, 15, 0, 0},
                {9, 10, 0, 11, 0, 2},
                {0, 15, 11, 0, 6, 0},
                {0, 0, 0, 6, 0, 9},
                {14, 0, 2, 0, 9, 0}
        };
    }

    @Test
    public void dijkstrasAlgorithm()
    {
        ArrayList<DijkstrasReturnType> returnValues = GreedyAlgorithms.DijkstrasAlgorithm(graph, 0, graph.length);

        int[] expectedDistances = {0, 7, 9, 20, 20, 11};
        int[] distances = returnValues.get(returnValues.size() - 1).getDistances();

        Assert.assertEquals(expectedDistances.length, distances.length);
        for (int i = 0; i < distances.length; i++)
        {
            Assert.assertEquals(expectedDistances[i], distances[i]);
        }
    }
}