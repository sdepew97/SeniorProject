package com.example.apphomepages.General.DataTypes;

public class DijkstrasReturnType
{
    private int[] distances;
    private Boolean[] processed;
    private int nodeIDBeingScanned;
    private int minimumNodeId;
    private boolean scanningForMin;

    public DijkstrasReturnType(int[] distances, Boolean[] processed, int nodeIDBeingScanned, int minimumNodeId, boolean scanningForMin)
    {
        this.distances = distances;
        this.processed = processed;
        this.nodeIDBeingScanned = nodeIDBeingScanned;
        this.minimumNodeId = minimumNodeId;
        this.scanningForMin = scanningForMin;
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

    public boolean isScanningForMin()
    {
        return scanningForMin;
    }
}
