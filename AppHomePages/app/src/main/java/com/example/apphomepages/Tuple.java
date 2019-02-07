package com.example.apphomepages;

import java.util.ArrayList;

public class Tuple {

    private ArrayList<Integer> list;
    private Integer swap1;
    private Integer swap2;

    public Tuple() {
        this.list = new ArrayList<>();
        swap1 = -1;
        swap2 = -1;
    }

    public Tuple(ArrayList<Integer> list) {
        this.list = list;
        swap1 = -1;
        swap2 = -1;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    public void setList(ArrayList<Integer> list) {
        this.list = list;
    }

    public void setSwap1(Integer swap1) {
        this.swap1 = swap1;
    }

    public void setSwap2(Integer swap2) {
        this.swap2 = swap2;
    }

    public ArrayList<Integer> constructPair() {
        ArrayList<Integer> returnVal = new ArrayList<>();

        returnVal.add(swap1);
        returnVal.add(swap2);

        return returnVal;
    }
}

