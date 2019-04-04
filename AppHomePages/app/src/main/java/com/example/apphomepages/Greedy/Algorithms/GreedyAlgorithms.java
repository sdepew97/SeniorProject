package com.example.apphomepages.Greedy.Algorithms;

import com.example.apphomepages.General.DataTypes.DijkstrasReturnType;

import java.util.ArrayList;
import java.util.Arrays;

public class GreedyAlgorithms
{
    //Used https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/ as a resource and copied, pasted, and modified methods, below, from this website. Also used https://en.wikipedia.org/wiki/Dijkstra's_algorithm as a resource.
    // A utility function to find the vertex with minimum distance value,
    // from the set of vertices not yet included in shortest path tree

    public static ArrayList<DijkstrasReturnType> DijkstrasAlgorithm(int[][] graph, int src, int numNodes)
    {
        //Return values that are snapshots of information from the array
        ArrayList<DijkstrasReturnType> returnValues = new ArrayList<>();

        int dist[] = new int[numNodes]; // The output array. dist[i] will hold
        // the shortest distance from src to i

        // sptSet[i] will true if vertex i is included in shortest
        // path tree or shortest distance from src to i is finalized
        Boolean sptSet[] = new Boolean[numNodes];

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < numNodes; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        //Initial frame
        returnValues.add(new DijkstrasReturnType(Arrays.copyOf(dist, numNodes), Arrays.copyOf(sptSet, numNodes), -1, -1)); //we want -1 here, since this lets us draw the initial node as basic at the beginning

        // Find shortest path for all vertices
        for (int count = 0; count < numNodes - 1; count++)
        {
            // Pick the minimum distance vertex from the set of vertices
            // not yet processed. u is always equal to src in first
            // iteration.
            int u = minDistance(dist, sptSet, numNodes);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            //Mark the picked node
            returnValues.add(new DijkstrasReturnType(Arrays.copyOf(dist, numNodes), Arrays.copyOf(sptSet, numNodes), u, u));

            // Update dist value of the adjacent vertices of the
            // picked vertex.
            for (int v = 0; v < numNodes; v++)
            {
                // Update dist[v] only if is not in sptSet, there is an
                // edge from u to v, and total weight of path from src to
                // v through u is smaller than current value of dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && //this 0 means that all edges in the graph must have non-zero weight!!
                        dist[u] != Integer.MAX_VALUE)
                {
                    returnValues.add(new DijkstrasReturnType(Arrays.copyOf(dist, numNodes), Arrays.copyOf(sptSet, numNodes), v, u));

                    if (dist[u] + graph[u][v] < dist[v])
                    {
                        dist[v] = dist[u] + graph[u][v];
                        returnValues.add(new DijkstrasReturnType(Arrays.copyOf(dist, numNodes), Arrays.copyOf(sptSet, numNodes), v, u));
                    }
                }
            }
        }

        //Final frame
        returnValues.add(new DijkstrasReturnType(Arrays.copyOf(dist, numNodes), Arrays.copyOf(sptSet, numNodes), -1, -1));

        return returnValues;
    }

    private static int minDistance(int dist[], Boolean sptSet[], int numNodes)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < numNodes; v++)
            if (!sptSet[v] && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }
}
