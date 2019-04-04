package com.example.apphomepages.General.HelperMethods;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.Graph.Algorithms.GraphAlgorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelperMethods
{
    //Method that allows you to generate an array using the random input, the bound on elements, and the size of the size input
    public static ArrayList<Integer> generateRandomArray(Random r, int size) throws IndexOutOfBoundsException
    {
        if (size < 1)
        {
            throw new IndexOutOfBoundsException();
        }

        ArrayList<Integer> numbers = new ArrayList<>(size);

        for (int j = 0; j < size; j++)
        {
            int randomInt = r.nextInt(100);
            if (r.nextBoolean())
            {
                numbers.add(j, -randomInt);
            } else
            {
                numbers.add(j, randomInt);
            }
        }

        return numbers;
    }

    public static <A> Graph<A> generateGraph(boolean binaryTree, List<A> nodeValues)
    {
        ArrayList<Node<A>> nodes = new ArrayList<>();
        Random r = new Random();

        //Add all the nodes
        for (int i = 0; i < nodeValues.size(); i++)
        {
            nodes.add(new Node<>(nodeValues.get(i), i)); //i is the nodeId value
        }

        //Make connections
        if (binaryTree)
        {
            //if we have only one node, then that is a complete binary tree already
            if (nodes.size() > 1)
            {
                int currentNode = 1;
                //build binary tree
                for (int i = 0; i < nodes.size(); i++)
                {
                    //give each node two children
                    for (int j = 0; j < 2; j++)
                    {
                        if (currentNode == nodeValues.size())
                        {
                            break;
                        }

                        //Add the connection
                        nodes.get(i).addAdjacentNode(nodes.get(currentNode));
                        nodes.get(currentNode).addAdjacentNode(nodes.get(i));
                        currentNode++;
                    }
                }
            }

        } else
        {
            //If we only have one node, then we have a complete graph, already
            if (nodes.size() > 1)
            {
                //TODO: ask Richard about this code for graph generation? Need to use something like Prim's?
                for (int i = 0; i < nodes.size(); i++)
                {
                    //not a tree, so can have cycles, etc
                    //Give the numConnections at least one child
                    while (nodes.get(i).getAdjacentNodes().size() < 1)
                    {
                        int connectionPoint;
                        //Make sure there are no self-looping nodes, since that's not allowed on the graph we're building
                        do
                        {
                            connectionPoint = r.nextInt(nodeValues.size());
                        } while (connectionPoint == i);

                        //Add connection, as long as it is not repeated
                        if (!nodes.get(i).getAdjacentNodes().contains(nodes.get(connectionPoint)))
                        {
                            nodes.get(i).addAdjacentNode(nodes.get(connectionPoint));
                            nodes.get(connectionPoint).addAdjacentNode(nodes.get(i));
                        }
                    }
                }

                //Code to check the graph is connected
                ArrayList<Node<A>> traversal = GraphAlgorithms.breadthFirstSearch(new Graph<>(nodes), new Node<A>(null, -1), true);

                //If this is the case, the graph is not connected
                if (traversal.size() != nodeValues.size())
                {
                    //add edges until we have a connected graph
                    while (traversal.size() != nodeValues.size())
                    {
                        Node<A> node = traversal.get(0); //we know there is at least one element here, since our graph has 2 or more elements
                        //Find a node not yet contained in the traversal to connect this node to
                        for (Node<A> n : nodes)
                        {
                            if (!traversal.contains(n))
                            {
                                //make connection
                                node.addAdjacentNode(n);
                                n.addAdjacentNode(node);
                            }
                        }
                        traversal = GraphAlgorithms.breadthFirstSearch(new Graph<>(nodes), new Node<A>(null, -1), true);
                    }
                }
            }
        }

        return new Graph<>(nodes);
    }

    //Convert an array list of type A to an array list of strings
    public static <A> ArrayList<String> convertToStrings(List<A> arrayList)
    {
        ArrayList<String> numbersStrings = new ArrayList<>();
        int numElements = arrayList.size();

        for (int k = 0; k < numElements; k++)
        {
            numbersStrings.add(arrayList.get(k).toString());
        }

        return numbersStrings;
    }

    public static Integer[] convertToArray(ArrayList<Integer> arrayList)
    {
        Integer[] aArray = new Integer[arrayList.size()];

        for (int i = 0; i < arrayList.size(); i++)
        {
            aArray[i] = arrayList.get(i);
        }

        return aArray;
    }

    public static <A> ArrayList<A> convertFromArray(A[] array)
    {
        ArrayList<A> aArray = new ArrayList<>();

        for (A a : array)
        {
            aArray.add(a);
        }

        return aArray;
    }
}
