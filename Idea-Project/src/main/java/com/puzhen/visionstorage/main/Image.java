package com.puzhen.visionstorage.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Image {

    // pixels in a row
    private int m;

    // pixels in a column
    private int n;

    private List<SimplePixel> pixelList = new ArrayList<>();

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<SimplePixel> getPixelList() {
        return pixelList;
    }

    public void setPixelList(List<SimplePixel> pixelList) {
        this.pixelList = pixelList;
    }

    public static Image createImageFromFile(String filepath) {
        Image image = new Image();
        List<SimplePixel> pixelList = image.getPixelList();
        int lineCount = 0;
        try {
            BufferedReader rd = new BufferedReader(new FileReader(new File(filepath)));
            String line = rd.readLine();
            lineCount++;
            String[] elements = line.split(" ");
            image.setM(elements.length);
            for (String element : elements) {
                String[] threeDimensions = element.split(",");
                SimplePixel simplePixel = new SimplePixel();
                simplePixel.setValue1(Integer.valueOf(threeDimensions[0]));
                simplePixel.setValue2(Integer.valueOf(threeDimensions[1]));
                simplePixel.setValue3(Integer.valueOf(threeDimensions[2]));
                pixelList.add(simplePixel);
            }

            while ((line = rd.readLine()) != null) {
                lineCount++;
                elements = line.split(" ");
                for (String element : elements) {
                    String[] threeDimensions = element.split(",");
                    SimplePixel simplePixel = new SimplePixel();
                    simplePixel.setValue1(Integer.valueOf(threeDimensions[0]));
                    simplePixel.setValue2(Integer.valueOf(threeDimensions[1]));
                    simplePixel.setValue3(Integer.valueOf(threeDimensions[2]));
                    pixelList.add(simplePixel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        image.setN(lineCount);
        return image;
    }
}
