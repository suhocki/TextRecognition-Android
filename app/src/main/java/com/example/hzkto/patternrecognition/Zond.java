package com.example.hzkto.patternrecognition;

/**
 * Created by hzkto on 10/17/2016.
 */

public class Zond {
    public int x1;
    public int x2;
    public int y1;
    public int y2;
    public int length;
    public int[] xArray;
    public int[] yArray;
    public int cross;

    public Zond(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        xArray = new int[40];
        yArray = new int[40];
        cross = 0;
    }
}
