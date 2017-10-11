package com.puzhen.visionstorage.main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Processing data...");
        // process data
        System.out.println("Finish processing.");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Please enter the first pixel, like x,y: ");
            String firstPixel = scanner.nextLine();
            if (firstPixel.equalsIgnoreCase("q")) break;
            System.out.print("Please enter the second pixel, like x,y: ");
            String secondPixel = scanner.nextLine();
            if (secondPixel.equalsIgnoreCase("q")) break;
            System.out.println("Their distance is: " + firstPixel + "---" + secondPixel);
        }

        scanner.close();
        System.out.println("Bye~");
    }
}
