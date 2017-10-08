package com.puzhen.visionstorage.trial;

import java.io.*;

public class PlayCreatingMillonFiles {

    public static void main(String[] args) throws IOException{
        File directory = new File("test-files");
        if (!directory.exists())
            directory.mkdir();
        for (int i = 0; i < 10000; i++) {
            File file = new File("test-files/" + i);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("000\r\n");
            writer.flush();
            writer.close();
        }
    }
}
