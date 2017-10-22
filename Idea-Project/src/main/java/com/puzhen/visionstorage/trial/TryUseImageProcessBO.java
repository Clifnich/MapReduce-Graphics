package com.puzhen.visionstorage.trial;

import com.puzhen.visionstorage.main.Image;
import com.puzhen.visionstorage.main.ImageProcessBO;

public class TryUseImageProcessBO {

    public static void main(String[] args) {
        ImageProcessBO imageProcessBO = ImageProcessBO.getInstance();
        //imageProcessBO.process(Image.createImageFromFile("test-data/50x50.txt"));
        System.out.println("This should be 161: " + imageProcessBO.getDistance("1 725"));
        imageProcessBO.shutdown();
    }
}
