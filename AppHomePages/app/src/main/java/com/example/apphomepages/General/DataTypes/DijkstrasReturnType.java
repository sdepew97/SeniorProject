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

    public void setDistances(int[] distances)
    {
        this.distances = distances;
    }

    public Boolean[] getProcessed()
    {
        return processed;
    }

    public void setProcessed(Boolean[] processed)
    {
        this.processed = processed;
    }

    public int getNodeIDBeingScanned()
    {
        return nodeIDBeingScanned;
    }

    public void setNodeIDBeingScanned(int nodeIDBeingScanned)
    {
        this.nodeIDBeingScanned = nodeIDBeingScanned;
    }

    public int getMinimumNodeId()
    {
        return minimumNodeId;
    }

    public void setMinimumNodeId(int minimumNodeId)
    {
        this.minimumNodeId = minimumNodeId;
    }
}
