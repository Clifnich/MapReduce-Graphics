package com.puzhen.visionstorage.script;

import java.util.Scanner;

public class ComputeDistance {

    public static void main(String[] args) {
        System.out.println("Hello, this program can compute the distance between two pixels");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\r\nPlease enter the first pixel, each dimension should be separated by , : ");
            String pixel1 = scanner.nextLine();
            if (pixel1.equalsIgnoreCase("q")) break;
            System.out.print("Please enter the second pixel, each dimension should be separated by , : ");
            String pixel2 = scanner.nextLine();
            if (pixel2.equalsIgnoreCase("q")) break;
            int distance = computeDistance(pixel1, pixel2);
            System.out.println("The distance is: " + distance);
        }
        System.out.println("Bye~");
        scanner.close();
    }

    private static int computeDistance(String pixel1, String pixel2) {
        if (!pixel1.contains(","))
            return Math.abs(Integer.valueOf(pixel1) - Integer.valueOf(pixel2));
        int distance = 0;
        String[] elementArray1 = pixel1.split(",");
        String[] elementArray2 = pixel2.split(",");
        for (int i = 0; i < elementArray1.length; i++) {
            distance += Math.pow(Integer.valueOf(elementArray1[i]) - Integer.valueOf(elementArray2[i]), 2);
        }
        return (int) (Math.sqrt(distance) + 0.5);
    }
}
