package com.example.apphomepages.Graph.HelperMethods;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.DataTypes.Point;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphHelperMethods
{
    public static void populateSpinner(ArrayList<String> possibleTreeTypes, View view, Spinner spinner, int index)
    {
        //populate the spinner used https://stackoverflow.com/questions/20244419/android-spinner-populating-using-arrayliststring as a resource as well as https://stackoverflow.com/questions/10582283/how-to-populate-spinner-from-a-array-string
        ArrayAdapter adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, possibleTreeTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //Set spinner selection
        spinner.setSelection(index); //choose the element in the array to select automatically from the dropdown menu (a.k.a. spinner menu)
    }

    public static <A> int numLayers(Graph<A> g)
    {
        if (layersOfNodes(g) == null)
        {
            return 0;
        } else
            return layersOfNodes(g).size();
    }

    public static <A> Point[] placeNodes(Graph<A> g, int width, int height)
    {
        Point[] centers = new Point[g.getGraphElements().size()];

        //Initialize each point
        for (int i = 0; i < centers.length; i++)
        {
            centers[i] = new Point();
        }

        ArrayList<ArrayList<Node<A>>> nodeLayers = layersOfNodes(g);
        int radius = height / (nodeLayers.size() * 2);

        int heightValue = radius * 2;
        int i = 0;
        int left = 0;
        int top = radius;

        //Get the lists of values to place at each location
        for (ArrayList<Node<A>> nodesInLayer : nodeLayers)
        {
            left = width / (nodesInLayer.size() + 1);
            for (Node<A> n : nodesInLayer)
            {
                centers[i].setX(left);
                centers[i].setY(top);
                i++;
                left += width / (nodesInLayer.size() + 1);
            }
            top += heightValue;
        }

        return centers;
    }

    //TODO: add cycle breaking code and work on tests including multiple separate cycles in a graph
    private static <A> ArrayList<ArrayList<Node<A>>> layersOfNodes(Graph<A> g)
    {
        //Divides the nodes in a graph into layers using the breadth first search algorithm
        int currentLayer = 0;

        ArrayList<Node<A>> vertices = g.getGraphElements();

        if (vertices.size() == 0)
        {
            return null;
        }

        //Now we know we have some vertices in the graph
        for (Node<A> n : vertices)
        {
            n.setVisited(false);
        }

        ArrayList<ArrayList<Node<A>>> orderVisited = new ArrayList<>();

        //Start with the root(s) of the graph (nodes that have no elements going into them...)
        ArrayList<Node<A>> currentLayerNodes = getIsolated(vertices);

        //Mark all of them!
        for (Node<A> n : currentLayerNodes)
            n.mark();

        //The starting layer
        orderVisited.add(currentLayerNodes);

        while (!allNodesMarked(g))
        {
            orderVisited.add(currentLayer + 1, new ArrayList<Node<A>>()); //initialize
            for (Node<A> n : currentLayerNodes)
            {
                ArrayList<Node<A>> adjacent = n.getAdjacentNodes();

                for (int i = adjacent.size() - 1; i >= 0; i--)
                {
                    Node a = adjacent.get(i);
                    if (!a.isVisited())
                    {
                        orderVisited.get(currentLayer + 1).add(a);
                        a.mark();
                    }
                }

            }

            currentLayer++;
            currentLayerNodes = orderVisited.get(currentLayer);
        }

        return orderVisited;
    }

    private static <A> boolean allNodesMarked(Graph<A> g)
    {
        for (Node<A> n : g.getGraphElements())
        {
            if (!n.isVisited())
            {
                return false;
            }
        }

        return true;
    }

    public static <A> ArrayList<Node<A>> getIsolated(ArrayList<Node<A>> nodes)
    {
        ArrayList<Node<A>> isolatedNodes = new ArrayList<>();
        boolean[] visited = new boolean[nodes.size()];

        for (int k = 0; k < visited.length; k++)
        {
            visited[k] = false;
        }

        for (int i = 0; i < nodes.size(); i++)
        {
            for (Node<A> a : nodes.get(i).getAdjacentNodes())
            {
                //TODO: if not here, then error!
                visited[nodes.indexOf(a)] = true;
            }
        }

        for (int j = 0; j < visited.length; j++)
        {
            if (!visited[j])
            {
                isolatedNodes.add(nodes.get(j));
            }
        }

        //pick the 0th node as the initial
        if (isolatedNodes.size() == 0)
        {
            isolatedNodes.add(nodes.get(0));
        }


        return isolatedNodes;
    }

    public static <A> HashMap<Node<A>, Integer> computeDegree(ArrayList<Node<A>> nodes)
    {
        HashMap<Node<A>, Integer> nodeCount = new HashMap<>();

        //Initialize all node degrees as 0
        for (int i = 0; i < nodes.size(); i++)
        {
            nodeCount.put(nodes.get(i), 0);
        }

        for (int i = 0; i < nodes.size(); i++)
        {
            for (Node<A> a : nodes.get(i).getAdjacentNodes())
            {
                Integer currentDegree = nodeCount.get(a);
                if (currentDegree != null)
                {
                    Integer newDegree = currentDegree + 1;
                    nodeCount.put(a, newDegree);
                }
            }
        }

        return nodeCount;
    }
}
