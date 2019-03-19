package com.example.apphomepages;

import android.content.Context;
import android.view.View;
import android.widget.Spinner;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.Graph.HelperMethods.GraphHelperMethods;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.HashMap;

public class GraphHelperMethodsTest
{

    @Test
    public void populateSpinner()
    {
        View v = Mockito.mock(View.class);
        Context c = Mockito.mock(Context.class);
        Spinner s = new Spinner(c);
        ArrayList<String> stringArrayList = new ArrayList<>();

        stringArrayList.add("Sarah");
        stringArrayList.add("Grace");
        stringArrayList.add("Nancy");

        GraphHelperMethods.populateSpinner(stringArrayList, v, s, 0);

        Assert.assertEquals(0, s.getSelectedItemPosition());
    }

    @Test
    public void numLayersEmpty()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        Graph<Integer> g = new Graph<>(nodes);

        Assert.assertEquals(0, GraphHelperMethods.numLayers(g));
    }

    @Test
    public void numLayersNonEmptyOne()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12, 0));
        nodes.add(new Node<>(14, 1));
        nodes.add(new Node<>(23, 2));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(1, GraphHelperMethods.numLayers(g));
    }

    @Test
    public void numLayersNonEmptyCycle()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12, 0));
        nodes.add(new Node<>(14, 1));
        nodes.add(new Node<>(23, 2));
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(3, GraphHelperMethods.numLayers(g));
    }

    //TODO: Talk with Richard about the types of graphs allowed...
    /*
    @Test
    public void numLayersNonEmptyOneIsolated()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12));
        nodes.add(new Node<>(14));
        nodes.add(new Node<>(23));
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(2, GraphHelperMethods.numLayers(g));
    }
    */

    //It was difficult to figure out how to test place nodes...
    @Test
    public void placeNodesEmpty()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        Graph<Integer> g = new Graph<>(nodes);

        Assert.assertEquals(0, GraphHelperMethods.placeNodes(g, 10, 10).length);
    }

    @Test
    public void placeNodesNonEmpty()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12, 0));
        nodes.add(new Node<>(14, 1));
        nodes.add(new Node<>(23, 2));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(3, GraphHelperMethods.placeNodes(g, 10, 10).length);
    }

    @Test
    public void getIsolatedOrRootTest1()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12, 0));
        nodes.add(new Node<>(14, 1));
        nodes.add(new Node<>(23, 2));
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(1, GraphHelperMethods.getIsolatedOrRoot(g.getGraphElements()).size());
    }

    @Test
    public void getIsolatedOrRootTest2()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12, 0));
        nodes.add(new Node<>(14, 1));
        nodes.add(new Node<>(23, 2));
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(1, GraphHelperMethods.getIsolatedOrRoot(g.getGraphElements()).size());
    }

    @Test
    public void computeDegreeEmpty()
    {
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        Assert.assertEquals(0, GraphHelperMethods.computeDegree(nodes).size());
    }

    @Test
    public void computeDegreeNonEmpty()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(1, 0));
        nodes.add(new Node<>(2, 1));
        nodes.add(new Node<>(3, 2));
        nodes.add(new Node<>(4, 3));
        nodes.add(new Node<>(5, 4));
        nodes.add(new Node<>(6, 5));

        //Connections to 1
        nodes.get(3).addAdjacentNode(nodes.get(0));
        nodes.get(4).addAdjacentNode(nodes.get(0));
        nodes.get(5).addAdjacentNode(nodes.get(0));

        //Connections to 2
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(4).addAdjacentNode(nodes.get(1));

        //Connections to 3
        nodes.get(1).addAdjacentNode(nodes.get(2));
        nodes.get(5).addAdjacentNode(nodes.get(2));

        //Connections to 4
        nodes.get(0).addAdjacentNode(nodes.get(3));

        //Connections to 5

        //Connections to 6
        nodes.get(1).addAdjacentNode(nodes.get(5));

        HashMap<Node<Integer>, Integer> degrees = GraphHelperMethods.computeDegree(nodes);
        Assert.assertEquals(new Integer(3), degrees.get(nodes.get(0)));
        Assert.assertEquals(new Integer(2), degrees.get(nodes.get(1)));
        Assert.assertEquals(new Integer(2), degrees.get(nodes.get(2)));
        Assert.assertEquals(new Integer(1), degrees.get(nodes.get(3)));
        Assert.assertEquals(new Integer(0), degrees.get(nodes.get(4)));
        Assert.assertEquals(new Integer(1), degrees.get(nodes.get(5)));
    }
}