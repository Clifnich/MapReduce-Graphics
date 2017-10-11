package com.puzhen.visionstorage.trial;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TwoBufferedReadersReadOneFile {

    private static final File file = new File("shared-file");

    private static Runnable r = () -> {
        try {
            BufferedReader rd = new BufferedReader(new FileReader(file));
            String line = "";
            StringBuffer sb = new StringBuffer();
            while ((line = rd.readLine()) != null)
                sb.append(line);
            rd.close();
            System.out.println(Thread.currentThread() + ", " + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) {
        new Thread(r).start();
        new Thread(r).start();
    }

}
