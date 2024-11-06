package com.Blood.Ware.utils.search;




public class Blockinfo {

    private int x;
    private int y;
    private int z;
    private float r;
    private float g;
    private float b;

    public Blockinfo(int x, int y, int z, float r, float g, float b) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public float getR() {
        return r;
    }

    public float getG() {
        return g;
    }

    public float getB() {
        return b;
    }
}
