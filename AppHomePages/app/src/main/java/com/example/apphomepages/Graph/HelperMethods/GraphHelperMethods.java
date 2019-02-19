package com.example.apphomepages.Graph.HelperMethods;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.DataTypes.Point;

import java.util.ArrayList;

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

    public static int numLayers(Graph g) {
        return layersOfNodes(g).size();
    }

    public static Point[] placeNodes(Graph g, int width, int height)
    {
        Point[] centers = new Point[g.numNodes()];

        //Initialize each point
        for (int i = 0; i < centers.length; i++)
        {
            centers[i] = new Point();
        }

        ArrayList<ArrayList<Node>> nodeLayers = layersOfNodes(g);
        int radius = height / (nodeLayers.size()*2);

        int heightValue = radius*2;
        int i = 0;
        int left = 0;
        int top = radius;

        //Get the lists of values to place at each location
        for (ArrayList<Node> nodesInLayer : nodeLayers)
        {
            left = width / (nodesInLayer.size()+1);
            for (Node n : nodesInLayer)
            {
                centers[i].setX(left);
                centers[i].setY(top);
                i++;
                left += width / (nodesInLayer.size()+1);
            }
            top += heightValue;
        }

        return centers;
    }

    private static ArrayList<ArrayList<Node>> layersOfNodes(Graph g)
    {
        //Divides the nodes in a graph into layers using the breadth first search algorithm
        ArrayList<ArrayList<Integer>> layers = new ArrayList<>();
        int currentLayer = 0;

        ArrayList<Node> vertices = g.getGraphElements();

        for (Node n : vertices)
        {
            n.setVisited(false);
        }

        ArrayList<ArrayList<Node>> orderVisited = new ArrayList<>();

        //Start with the root of the graph
        vertices.get(0).mark();
        ArrayList<Node> currentLayerNodes = new ArrayList<>();
        currentLayerNodes.add(currentLayer, vertices.get(0));
        orderVisited.add(currentLayerNodes); //starting layer

        while (!allNodesMarked(g))
        {
            orderVisited.add(currentLayer + 1, new ArrayList<Node>()); //initialize
            for (Node n : currentLayerNodes)
            {
                ArrayList<Node> adjacent = n.getAdjacentNodes();

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

    private static boolean allNodesMarked(Graph g)
    {
        for (Node n : g.getGraphElements())
        {
            if (!n.isVisited())
            {
                return false;
            }
        }

        return true;
    }

}
