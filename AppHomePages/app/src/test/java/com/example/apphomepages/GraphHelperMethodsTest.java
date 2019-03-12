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
        nodes.add(new Node<>(12));
        nodes.add(new Node<>(14));
        nodes.add(new Node<>(23));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(1, GraphHelperMethods.numLayers(g));
    }

    @Test
    public void numLayersNonEmptyCycle()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12));
        nodes.add(new Node<>(14));
        nodes.add(new Node<>(23));
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(3, GraphHelperMethods.numLayers(g));
    }

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

    @Test
    public void placeNodes()
    {

    }

    @Test
    public void getIsolatedTest1()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12));
        nodes.add(new Node<>(14));
        nodes.add(new Node<>(23));
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(0));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(1, GraphHelperMethods.getIsolated(g.getGraphElements()).size());
    }

    /*
    @Test
    public void getIsolatedTest2()
    {
        //Create the nodes that go in the graph
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        nodes.add(new Node<>(12));
        nodes.add(new Node<>(14));
        nodes.add(new Node<>(23));
        nodes.get(0).addAdjacentNode(nodes.get(1));
        nodes.get(1).addAdjacentNode(nodes.get(2));
        nodes.get(2).addAdjacentNode(nodes.get(0));

        Graph<Integer> g = new Graph<>(nodes);
        Assert.assertEquals(0, GraphHelperMethods.getIsolated(g.getGraphElements()).size());
    }
    */

    @Test
    public void computeDegree()
    {
    }
}