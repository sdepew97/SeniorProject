package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.Point;

import org.junit.Assert;
import org.junit.Test;

public class PointTest
{

    @Test
    public void setX()
    {
        Point p = new Point();
        p.setX(223);

        Assert.assertEquals(223, p.getX());
    }

    @Test
    public void setY()
    {
        Point p = new Point();
        p.setY(122);

        Assert.assertEquals(122, p.getY());
    }

    @Test
    public void getX()
    {
        Point p = new Point(28, 123);

        Assert.assertEquals(28, p.getX());
    }

    @Test
    public void getY()
    {
        Point p = new Point(28, 123);

        Assert.assertEquals(123, p.getY());
    }
}