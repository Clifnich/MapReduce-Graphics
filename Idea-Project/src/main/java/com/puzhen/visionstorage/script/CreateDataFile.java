package com.puzhen.visionstorage.script;

import java.io.*;

public class CreateDataFile {

    public static void main(String[] args) throws IOException {
        File file = new File("data");
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file)));
        int column = 1000, row = 1000;
        for (int i = 0; i < row; i++) {
            StringBuffer sb = new StringBuffer();
            boolean first = true;
            for (int j = 0; j < column; j++) {
                if (first) {
                    sb.append("1,2,3");
                    first = false;
                } else {
                    sb.append(" 1,2,3");
                }
            }
            writer.write(sb.toString());
            writer.write("\r\n");
        }
        writer.flush();
        writer.close();
    }
}
