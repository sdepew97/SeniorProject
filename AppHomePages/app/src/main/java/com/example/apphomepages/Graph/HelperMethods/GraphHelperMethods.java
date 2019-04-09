package com.example.apphomepages.Graph.HelperMethods;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.DataTypes.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

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

    public static <A> int getNodeIndexBasedOnId(ArrayList<Node<A>> nodes, Integer nodeId)
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            if (nodes.get(i).getNodeId().equals(nodeId))
            {
                return i;
            }
        }

        return -1;
    }

    public static <A> int numLayers(Graph<A> g)
    {
        ArrayList<ArrayList<Node<A>>> list = layersOfNodes(g);
        if (list == null)
        {
            return 0;
        } else
            return list.size();
    }

    private static <A> ArrayList<ArrayList<Node<A>>> layersOfNodes(Graph<A> g)
    {
        ArrayList<Node<A>> vertices = g.getGraphElements();

        if (vertices.size() == 0)
        {
            return new ArrayList<>();
        }

        //Order in which the nodes are visited
        ArrayList<Node<A>> visitOrder = new ArrayList<>();

        //Now we know we have some vertices in the graph
        for (Node<A> n : vertices)
        {
            n.setVisited(false);
        }

        Queue<Node<A>> queue = new LinkedList<>();

        //Start with the root(s) of the graph (nodes that have no elements going into them...)
        ArrayList<Node<A>> isolated = getIsolatedOrRoot(vertices);

        //Mark all of them!
        for (Node<A> n : isolated)
        {
            n.mark();
            queue.add(n);
        }

        //Add the first marker
        Node<A> marker1 = new Node<>(null, -1);
        queue.add(marker1);

        while (queue.size() != 0)
        {
            Node<A> n = queue.poll();

            //when you pop a marker, if there are more nodes to look at, add a new one
            if (n.getNodeValue() == null && queue.size() != 0)
            {
                Node<A> marker = new Node<>(null, -1);
                visitOrder.add(marker);
                queue.add(marker);
            } else
            {
                visitOrder.add(n);

                ArrayList<Node<A>> adjacent = n.getAdjacentNodes();
                for (Node<A> a : adjacent)
                {
                    if (!a.isVisited())
                    {
                        a.mark();
                        queue.add(a);
                    }
                }
            }
        }

        //Take the list of visited nodes and create the return value
        ArrayList<ArrayList<Node<A>>> orderVisited = new ArrayList<>();
        ArrayList<Node<A>> currentLayer = new ArrayList<>();

        for (Node<A> n : visitOrder)
        {
            if (n.getNodeValue() != null)
            {
                currentLayer.add(n);
            } else
            {
                orderVisited.add(currentLayer);
                currentLayer = new ArrayList<>();
            }
        }

        return orderVisited;
    }

    public static <A> ArrayList<Node<A>> getIsolatedOrRoot(ArrayList<Node<A>> nodes)
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

    public static <A> Point[] placeNodes(Graph<A> g, int width, int height)
    {
        if (g.getGraphElements().size() == 0)
        {
            return new Point[0];
        }

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
        int left;
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

    public static <A> Graph<A> trimNode(Graph<A> graph, Node<A> node)
    {
        ArrayList<Node<A>> nodes = graph.getGraphElements();
        ArrayList<Node<A>> revisedNodes = new ArrayList<>();

        for (Node<A> n : nodes)
        {
            if (!n.getNodeId().equals(node.getNodeId()))
            {
                revisedNodes.add(n);
            }
        }

        //remove any unnecessary edges...
        for (Node<A> a : revisedNodes)
        {
            ArrayList<Node<A>> correctAdjacentNodes = new ArrayList<>();

            for (Node<A> adj : a.getAdjacentNodes())
            {
                if (!adj.getNodeId().equals(node.getNodeId()))
                {
                    correctAdjacentNodes.add(adj);
                }
            }

            a.setAdjacentNodes(correctAdjacentNodes);
        }

        return new Graph<>(revisedNodes);
    }

    public static <A> Graph<A> copyGraph(Graph<A> original)
    {
        ArrayList<Node<A>> originalNodes = original.getGraphElements();
        ArrayList<Node<A>> copyNodes = new ArrayList<>();

        //Add nodes
        for (Node<A> n : originalNodes)
        {
            copyNodes.add(new Node<>(n.getNodeValue(), n.getNodeId()));
        }

        //Add connections
        for (Node<A> n : originalNodes)
        {
            for (Node<A> adj : n.getAdjacentNodes())
            {
                for (Node<A> x : copyNodes)
                {
                    for (Node<A> y : copyNodes)
                    {
                        if (n.getNodeId().equals(x.getNodeId()) && adj.getNodeId().equals(y.getNodeId()))
                        {
                            x.addAdjacentNode(y);
                        }
                    }
                }
            }
        }

        return new Graph<>(copyNodes);
    }
}
