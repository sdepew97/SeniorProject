package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.DijkstrasReturnType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DijkstrasReturnTypeTest
{
    DijkstrasReturnType dijkstrasReturnType;

    @Before
    public void initialize()
    {
        int[] distances = {12};
        Boolean[] processed = {true};
        int nodeID = 0;
        int minNode = 0;
        boolean scanning = true;

        dijkstrasReturnType = new DijkstrasReturnType(distances, processed, nodeID, minNode, scanning);
    }


    @Test
    public void getDistances()
    {
        int[] distances = dijkstrasReturnType.getDistances();
        Assert.assertEquals(12, distances[0]);
    }

    @Test
    public void getProcessed()
    {
        Boolean[] processed = dijkstrasReturnType.getProcessed();
        Assert.assertEquals(true, processed[0]);
    }

    @Test
    public void getNodeIDBeingScanned()
    {
        int nodeID = dijkstrasReturnType.getNodeIDBeingScanned();
        Assert.assertEquals(0, nodeID);
    }

    @Test
    public void getMinimumNodeId()
    {
        int minNode = dijkstrasReturnType.getMinimumNodeId();
        Assert.assertEquals(0, minNode);
    }

    @Test
    public void isScanningForMin()
    {
        boolean scanning = dijkstrasReturnType.isScanningForMin();
        Assert.assertTrue(scanning);
    }
}