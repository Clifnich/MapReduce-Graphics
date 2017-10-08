package com.puzhen.visionstorage.script;

import java.io.*;

public class CreateDistances {

    public static void main(String[] args) throws IOException {
        File data = new File("data");
        File data2 = new File("data2");
        BufferedReader rd = new BufferedReader(new FileReader(data));
        String line = "";
        while ((line = rd.readLine()) != null) {
            String[] elements = line.split(" ");
            for (String pixel : elements) {

            }
        }
    }
}
