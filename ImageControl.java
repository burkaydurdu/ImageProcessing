package com.bdurdu;

import java.awt.image.BufferedImage;

public class ImageControl {

    private BufferedImage image;
    private MyColor color;

    public ImageControl(BufferedImage image) {
        this.image = image;
        color = new MyColor();
    }
    public ImageControl() {
        color = new MyColor();
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    private int getGrayValue(int pixel) {
        color.pixel(pixel);
        int avg = ( (color.RED + color.GREEN + color.BLUE) / 3);
        color.RED = avg;
        color.GREEN = avg;
        color.BLUE = avg;
//      color.RED = (int) (color.RED * 0.30);
//      color.GREEN = (int) (color.GREEN * 0.59);
//      color.BLUE = (int) (color.BLUE * 0.11);
        return color.intPixel();
    }

    public BufferedImage getGrayImage() {
        for(int i = 0; i < image.getHeight(); i++)
            for(int j = 0; j < image.getWidth(); j++) {
                image.setRGB(j, i, getGrayValue(image.getRGB(j, i)));
            }
        return image;
    }
}
