package com.example.apphomepages;

import java.util.ArrayList;

public class Pair {
    private ArrayList<Integer> list;
    private Integer minimum;

    public Pair() {
        this.list = new ArrayList<>();
        minimum = -1; //Default minimum is NOT an entry into the array
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public Integer getMinimum() {
        return minimum;
    }

    public void setMinimum(Integer minimum) {
        this.minimum = minimum;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public ArrayList<Integer> constructList() {
        ArrayList<Integer> returnVal = new ArrayList<>();

        returnVal.add(minimum);

        return returnVal;
    }
}
