package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.SortReturnType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class SortReturnTypeTest
{
    private SortReturnType sortReturnType;

    @Before
    public void initialize()
    {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);

        sortReturnType = new SortReturnType(list);
    }

    @Test
    public void getList()
    {
        ArrayList<Integer> list = sortReturnType.getList();

        for (int i = 0; i < list.size(); i++)
        {
            Assert.assertEquals(new Integer(i), list.get(i));
        }
    }

    @Test
    public void setList()
    {
        ArrayList<Integer> newList = new ArrayList<>();
        newList.add(22);
        sortReturnType.setList(newList);

        Assert.assertEquals(new Integer(22), sortReturnType.getList().get(0));
    }

    @Test
    public void getA()
    {
        Assert.assertEquals(new Integer(-1), sortReturnType.getA());
    }

    @Test
    public void setA()
    {
        sortReturnType.setA(22);
        Assert.assertEquals(new Integer(22), sortReturnType.getA());
    }

    @Test
    public void getB()
    {
        Assert.assertEquals(new Integer(-1), sortReturnType.getB());
    }

    @Test
    public void setB()
    {
        sortReturnType.setB(22);
        Assert.assertEquals(new Integer(22), sortReturnType.getB());
    }

    @Test
    public void constructPair()
    {
        sortReturnType.setA(12);
        sortReturnType.setB(22);
        ArrayList<Integer> pair = sortReturnType.constructPair();

        Assert.assertEquals(new Integer(12), pair.get(0));
        Assert.assertEquals(new Integer(22), pair.get(1));
    }
}