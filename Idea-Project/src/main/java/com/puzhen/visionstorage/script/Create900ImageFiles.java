package com.puzhen.visionstorage.script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Create900ImageFiles {

    private static final int NUM_OF_FILES = 900;
    private static final int NUM_OF_ROWS = 899;

    public static void main(String[] args) throws IOException{
        for (int i = 0; i < NUM_OF_FILES; i++) {
            File file = new File("30x30Image/" + i);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int j = 0; j < NUM_OF_ROWS; j++) {
                if (j == 0) {
                    writer.write("000");
                } else {
                    writer.write("\r\n000");
                }
            }
            writer.flush();
            writer.close();
        }
    }
}
