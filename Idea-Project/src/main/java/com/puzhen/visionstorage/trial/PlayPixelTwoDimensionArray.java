package com.puzhen.visionstorage.trial;

import com.puzhen.visionstorage.main.Pixel;

public class PlayPixelTwoDimensionArray {

    public static void main(String[] args) {
        Pixel[][] pixelArray = new Pixel[1][1];
        Pixel pixel = new Pixel();
        pixel.setRow(1);
        pixel.setColumn(1);
        int[] contents = {1,2};
        pixel.setContents(contents);
        pixelArray[0][0] = pixel;
        System.out.println(pixelArray[0][0]);
    }
}
