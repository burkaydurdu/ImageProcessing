package com.bdurdu;

public class MyColor {

    public int ALPHA;
    public int RED;
    public int GREEN;
    public int BLUE;

    public void pixel(int pixel) {
        ALPHA = (pixel >> 24) & 0xff;
        RED = (pixel >> 16) & 0xff;
        GREEN = (pixel >> 8) & 0xff;
        BLUE = (pixel) & 0xff;
    }
    public int intPixel() {
        return (ALPHA<<24) | (RED<<16) | (GREEN<<8) | BLUE;
    }
}
