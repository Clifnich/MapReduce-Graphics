package com.puzhen.visionstorage.main;

import java.io.*;

public class DistanceStore {

    private static DistanceStore ourInstance = new DistanceStore();
    private static final String DATA_DIRECTORY = "distance-store";

    public static DistanceStore getInstance() {
        return ourInstance;
    }

    private int rows;
    private int columns;

    private String filepath;

    private DistanceStore() {
        File dataDirectory = new File(DATA_DIRECTORY);
        if (!dataDirectory.exists())
            dataDirectory.mkdir();
    }

    public void configure(String filepath) throws FileNotFoundException{
        System.out.println("DistanceStore is configuring based on file: " + filepath);
        File file = new File(filepath);
        BufferedReader rd = new BufferedReader(new FileReader(file));
        this.filepath = filepath;
        int currentRow = 0;
        try {
            String line = "";
            boolean first = true;
            while ((line = rd.readLine()) != null) {
                String[] elements = line.split(" ");
                if (first) {
                    this.rows = Integer.valueOf(elements[0]);
                    this.columns = Integer.valueOf(elements[1]);
                    first = false;
                    currentRow = 1;
                } else {
                    // create data file
                    for (int i = 1; i <= this.columns; i++) {
                        createDistanceFile(currentRow, i, filepath);
                    }
                    currentRow++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDistanceFile(int row, int column, String filename) {
        System.out.println("Creating a data file for (" + row + ", " + column + ").");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    new File(DATA_DIRECTORY + File.separator + row + "," + column)));
            Pixel currentPixel = PixelFinder.find(row, column, filename);
            boolean firstWrite = true;
            for (int i = column + 1; i <= this.columns; i++) {
                Pixel pixel = PixelFinder.find(row, i, filename);
                int distance = computeDistance(pixel, currentPixel);
                if (firstWrite) {
                    writer.write(String.valueOf(distance));
                    firstWrite = false;
                } else {
                    writer.write("\r\n" + distance);
                }
            }
            for (int currentRow = row + 1; currentRow <= this.rows; currentRow++) {
                for (int currentColumn = 1; currentColumn <= this.columns; currentColumn++) {
                    Pixel pixel = PixelFinder.find(currentRow, currentColumn, filename);
                    int distance = computeDistance(pixel, currentPixel);
                    if (firstWrite) {
                        writer.write(String.valueOf(distance));
                        firstWrite = false;
                    } else {
                        writer.write("\r\n" + distance);
                    }
                }
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getDistance(int row1, int column1, int row2, int column2) {
        int distance = 0;
        try {
            Pixel pixel1 = PixelFinder.find(row1, column1, filepath);
            Pixel pixel2 = PixelFinder.find(row2, column2, filepath);
            distance = computeDistance(pixel1, pixel2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return distance;
    }

    /**
     * Compute the distance between two pixels
     * @param pixel1
     * @param pixel2
     */
    private int computeDistance(Pixel pixel1, Pixel pixel2) {
        int[] contents1 = pixel1.getContents();
        int[] contents2 = pixel2.getContents();
        int squareSum = 0;
        for (int i = 0; i < contents1.length; i++) {
            squareSum += (contents1[i] - contents2[i]) * (contents1[i] - contents2[i]);
        }
        return (int) Math.sqrt(squareSum);
    }
}
