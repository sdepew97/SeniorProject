package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.QuickSortReturnType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class QuickSortReturnTypeTest
{
    private QuickSortReturnType quickSortReturnType;

    @Before
    public void initialize()
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        quickSortReturnType = new QuickSortReturnType(list, 0, list.size(), list.size() - 1, 1, false);
    }

    @Test
    public void getList()
    {
        ArrayList<Integer> list = quickSortReturnType.getList();

        for (int i = 0; i < quickSortReturnType.getList().size(); i++)
        {
            Assert.assertEquals(new Integer(i), list.get(i));
        }
    }

    @Test
    public void setList()
    {
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(22);

        quickSortReturnType.setList(newList);
        Assert.assertEquals(new Integer(22), quickSortReturnType.getList().get(0));
    }

    @Test
    public void getA()
    {
        Assert.assertEquals(new Integer(0), quickSortReturnType.getA());
    }

    @Test
    public void setA()
    {
        quickSortReturnType.setA(22);
        Assert.assertEquals(new Integer(22), quickSortReturnType.getA());
    }

    @Test
    public void getB()
    {
        Assert.assertEquals(new Integer(6), quickSortReturnType.getB());
    }

    @Test
    public void getPivot()
    {
        Assert.assertEquals(new Integer(5), quickSortReturnType.getPivot());
    }

    @Test
    public void getBeingViewed()
    {
        Assert.assertEquals(new Integer(1), quickSortReturnType.getBeingViewed());
    }

    @Test
    public void isPartitioning()
    {
        Assert.assertTrue(!quickSortReturnType.isPartitioning());
    }
}