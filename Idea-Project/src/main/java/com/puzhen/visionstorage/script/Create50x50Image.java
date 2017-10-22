package com.puzhen.visionstorage.script;

import com.puzhen.visionstorage.main.ImageProcessBO;

public class Create50x50Image {

    public static void main(String[] args) {
        if (ImageProcessBO.createDataFile(50, 50, "test-data/50x50.txt")) {
            System.out.println("Done");
        } else {
            System.err.println("Error");
        }
    }
}
