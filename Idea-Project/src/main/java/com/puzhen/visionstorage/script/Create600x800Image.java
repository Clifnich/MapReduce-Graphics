package com.puzhen.visionstorage.script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Create600x800Image {

    private static final int NUM_OF_FILES = 10000;
    private static final int NUM_OF_ROWS = 480000;

    public static void main(String[] args) throws IOException{
        new Thread(r1).start();
        new Thread(r2).start();
    }

    private static Runnable r1 = () -> {
        for (int i = 0; i < (NUM_OF_FILES / 2); i++) {
            System.out.println(Thread.currentThread() + " is writing file #" + i);
            File file = new File("600x800Image/" + i);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (int j = 0; j < NUM_OF_ROWS; j++) {
                    if (j == 0)
                        writer.write("000");
                    else
                        writer.write("\r\n000");
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    private static Runnable r2 = () -> {
        for (int i = (NUM_OF_FILES / 2); i < NUM_OF_FILES; i++) {
            System.out.println(Thread.currentThread() + " is writing file #" + i);
            File file = new File("600x800Image/" + i);
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (int j = 0; j < NUM_OF_ROWS; j++) {
                    if (j == 0)
                        writer.write("000");
                    else
                        writer.write("\r\n000");
                }
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };
}
