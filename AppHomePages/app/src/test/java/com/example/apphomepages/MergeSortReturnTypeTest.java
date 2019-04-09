package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.MergeSortReturnType;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class MergeSortReturnTypeTest
{
    MergeSortReturnType mergeSortReturnType;

    @Before
    public void initialize()
    {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(0);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);

        mergeSortReturnType = new MergeSortReturnType(0, numbers.size() - 1, numbers, false);
    }

    @Test
    public void getLeft()
    {
        Assert.assertEquals(0, mergeSortReturnType.getLeft());
    }

    @Test
    public void getRight()
    {
        Assert.assertEquals(3, mergeSortReturnType.getRight());
    }

    @Test
    public void getNumbers()
    {
        for (int i = 0; i < mergeSortReturnType.getNumbers().size(); i++)
        {
            Assert.assertEquals(new Integer(i), mergeSortReturnType.getNumbers().get(i));
        }
    }

    @Test
    public void isMerging()
    {
        Assert.assertTrue(!mergeSortReturnType.isMerging());
    }
}