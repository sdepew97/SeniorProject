package com.example.apphomepages.General.DataTypes;

import java.util.Random;

public class Color
{
    private int red;
    private int blue;
    private int green;

    public Color(int red, int green, int blue)
    {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    //These colors are ones that are used the most in the program, so they are able to be easily referenced in the code
    //I used Google's color picker functionality for multiple of the colors, below
    public static Color getMain()
    {
        //https://www.rapidtables.com/web/color/RGB_Color.html used to get the colors
        return new Color(102, 102, 255);
    }

    public static Color randomColor()
    {
        Random r = new Random();
        return new Color(r.nextInt(256), r.nextInt(256), r.nextInt(256));
    }

    public static Color getSecondary()
    {
        return new Color(153, 255, 255);
    }

    public static Color getFound()
    {
        return new Color(255, 102, 178);
    }

    public static Color getCurrent()
    {
        return new Color(15, 188, 107);
    }

    public static Color getScan()
    {
        return new Color(176, 66, 244);
    }

    public int getRed()
    {
        return red;
    }

    public int getGreen()
    {
        return green;
    }

    public int getBlue()
    {
        return blue;
    }
}
