package com.nonagon.javajourneymanbadge.threads.beepBop;

public class Tally {
    int beepsBops = 0;
    String name;


    public int getBeepsBops() {
        return beepsBops;
    }

    public void addBeepsBops() {
        this.beepsBops++;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
