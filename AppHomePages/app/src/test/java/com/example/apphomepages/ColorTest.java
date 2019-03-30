package com.example.apphomepages;

import com.example.apphomepages.General.DataTypes.Color;

import org.junit.Assert;
import org.junit.Test;

public class ColorTest
{
    //Variables used during testing
    private static Color color = new Color(12, 12, 12);

    @Test
    public void getMain()
    {
        Color main = Color.getMain();
        Assert.assertEquals(102, main.getRed());
        Assert.assertEquals(102, main.getGreen());
        Assert.assertEquals(255, main.getBlue());
    }

    @Test
    public void randomColor()
    {
        Color randomColor = Color.randomColor();
        Assert.assertTrue((0 <= randomColor.getRed() && randomColor.getRed() <= 255) && (0 <= randomColor.getGreen() && randomColor.getGreen() <= 255) && (0 <= randomColor.getBlue() && randomColor.getBlue() <= 255));
    }

    @Test
    public void getSecondary()
    {
        Color secondary = Color.getSecondary();
        Assert.assertEquals(153, secondary.getRed());
        Assert.assertEquals(255, secondary.getGreen());
        Assert.assertEquals(255, secondary.getBlue());
    }

    @Test
    public void getFound()
    {
        Color found = Color.getFound();
        Assert.assertEquals(255, found.getRed());
        Assert.assertEquals(102, found.getGreen());
        Assert.assertEquals(178, found.getBlue());
    }

    @Test
    public void getCurrent()
    {
        Color found = Color.getCurrent();
        Assert.assertEquals(15, found.getRed());
        Assert.assertEquals(188, found.getGreen());
        Assert.assertEquals(107, found.getBlue());
    }

    @Test
    public void getRed()
    {
        Assert.assertEquals(12, color.getRed());
    }

    @Test
    public void getGreen()
    {
        Assert.assertEquals(12, color.getGreen());
    }

    @Test
    public void getBlue()
    {
        Assert.assertEquals(12, color.getBlue());
    }
}