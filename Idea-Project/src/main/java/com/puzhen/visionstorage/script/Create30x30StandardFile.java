package com.puzhen.visionstorage.script;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Create30x30StandardFile {

    public static void main(String[] args) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                new File("test-data" + File.separator + "30x30")));
        writer.write("30 30 3");
        for (int i = 0; i < 30; i++) {
            writer.write("\r\n");
            boolean firstInARow = true;
            for (int j = 0; j < 30; j++) {
                if (firstInARow) {
                    firstInARow = false;
                    writer.write(getRandomPixelString());
                } else {
                    writer.write(" " + getRandomPixelString());
                }
            }
        }
        writer.flush();
        writer.close();
    }

    private static String getRandomPixelString() {
        Random random = new Random();
        int x = random.nextInt(100),
                y = random.nextInt(100),
                z = random.nextInt(100);
        StringBuffer sb = new StringBuffer();
        sb.append(x);
        sb.append(",");
        sb.append(y);
        sb.append(",");
        sb.append(z);
        return sb.toString();
    }

}
