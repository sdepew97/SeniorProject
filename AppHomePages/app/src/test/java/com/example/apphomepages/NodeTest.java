package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.Node;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class NodeTest
{
    private Node<Integer> testNode1;
    private Node<Integer> testNode2;

    @Before
    public void initailize() {
        testNode1 = new Node<>(12, 0);
        testNode2 = new Node<>(22, 1);
    }

    @Test
    public void getNodeValue()
    {
        Assert.assertEquals(new Integer(12), testNode1.getNodeValue());
        Assert.assertEquals(new Integer(22), testNode2.getNodeValue());
    }

    @Test
    public void getNodeId()
    {
        Assert.assertEquals(new Integer(0), testNode1.getNodeId());
        Assert.assertEquals(new Integer(1), testNode2.getNodeId());
    }

    @Test
    public void getAdjacentNodes()
    {
        Assert.assertEquals(0, testNode1.getAdjacentNodes().size());
        Assert.assertEquals(0, testNode2.getAdjacentNodes().size());
    }

    @Test
    public void setAdjacentNodes()
    {
        ArrayList<Node<Integer>> testNode1Adj = new ArrayList<>();
        testNode1Adj.add(testNode2);
        testNode1.setAdjacentNodes(testNode1Adj);

        Assert.assertEquals(1, testNode1.getAdjacentNodes().size());
    }

    @Test
    public void addAdjacentNode()
    {
        testNode1.addAdjacentNode(testNode2);
        Assert.assertEquals(1, testNode1.getAdjacentNodes().size());
    }

    @Test
    public void isVisited()
    {
        Assert.assertTrue(!testNode1.isVisited());
        Assert.assertTrue(!testNode2.isVisited());
    }

    @Test
    public void setVisited()
    {
        testNode1.setVisited(true);
        testNode2.setVisited(true);

        Assert.assertTrue(testNode1.isVisited());
        Assert.assertTrue(testNode2.isVisited());
    }

    @Test
    public void mark()
    {
        testNode1.mark();
        testNode2.mark();

        Assert.assertTrue(testNode1.isVisited());
        Assert.assertTrue(testNode2.isVisited());
    }
}