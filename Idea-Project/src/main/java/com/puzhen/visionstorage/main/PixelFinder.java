package com.puzhen.visionstorage.main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PixelFinder {

    /**
     * Find a pixel in the given position of a file
     * @param row
     * @param column
     * @param filepath
     * @return
     * @throws IOException
     */
    public static Pixel find(int row, int column, String filepath) throws IOException{
        Pixel pixel = new Pixel();
        pixel.setRow(row);
        pixel.setColumn(column);
        BufferedReader rd = new BufferedReader(new FileReader(new File(filepath)));
        int dimensions = Integer.valueOf(rd.readLine().split(" ")[2]);
        for (int i = 1; i < row; i++)
            rd.readLine();
        String[] elements = rd.readLine().split(" ");
        String pixelString = elements[column - 1];
        if (dimensions == 1) {
            int[] contents = {Integer.valueOf(pixelString)};
            pixel.setContents(contents);
        } else {
            List<Integer> contents = new ArrayList<Integer>(dimensions);
            elements = pixelString.split(",");
            for (String element : elements) {
                contents.add(Integer.valueOf(element));
            }
            int[] contentArray = new int[dimensions];
            for (int i = 0; i < dimensions; i++)
                contentArray[i] = contents.get(i);
            pixel.setContents(contentArray);
        }
        return pixel;
    }
}
