package com.example.apphomepages.General.DataTypes;

public class DijkstrasReturnType
{
    private int[] distances;
    private Boolean[] processed;
    private int nodeIDBeingScanned;
    private int minimumNodeId;

    public DijkstrasReturnType(int[] distances, Boolean[] processed, int nodeIDBeingScanned, int minimumNodeId)
    {
        this.distances = distances;
        this.processed = processed;
        this.nodeIDBeingScanned = nodeIDBeingScanned;
        this.minimumNodeId = minimumNodeId;
    }

    public int[] getDistances()
    {
        return distances;
    }

    public Boolean[] getProcessed()
    {
        return processed;
    }

    public int getNodeIDBeingScanned()
    {
        return nodeIDBeingScanned;
    }

    public int getMinimumNodeId()
    {
        return minimumNodeId;
    }
}
