package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.Graph;
import com.example.apphomepages.General.DataTypes.Node;
import com.example.apphomepages.General.DataTypes.TopologicalOrderingReturnType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TopologicalOrderingReturnTypeTest
{
    private TopologicalOrderingReturnType returnType;

    @Before
    public void initialize()
    {
        returnType = new TopologicalOrderingReturnType();
    }

    @Test
    public void getVisitOrder()
    {
        Assert.assertEquals(0, returnType.getVisitOrder().size());
    }

    @Test
    public void setVisitOrder()
    {
        ArrayList<Integer> visitOrder = new ArrayList<>();
        visitOrder.add(0);
        returnType.setVisitOrder(visitOrder);

        Assert.assertEquals(1, returnType.getVisitOrder().size());
    }

    @Test
    public void getGraphs()
    {
        Assert.assertEquals(0, returnType.getGraphs().size());
    }

    @Test
    public void addGraph()
    {

        Node<String> node = new Node<>("2", 0);
        ArrayList<Node<String>> nodes = new ArrayList<>();
        nodes.add(node);
        returnType.addGraph(new Graph<>(nodes));

        Assert.assertEquals(1, returnType.getGraphs().size());

    }
}