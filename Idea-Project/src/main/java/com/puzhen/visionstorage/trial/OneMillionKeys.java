package com.puzhen.visionstorage.trial;

import java.util.*;

public class OneMillionKeys {

    public static void main(String[] args) {
        int counter = 0, cardinal = 10000;
        Map<String, Integer> map = new HashMap<String, Integer>();
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < cardinal; i++) {
            for (int j = 0; j < cardinal; j++) {
                if (counter > 100000) {
                    // prompt the user to press enter to continue
                    System.out.print("Please press enter to continue...");
                    String tru = scanner.next();
                    counter = 0;
                }
                StringBuffer key = new StringBuffer();
                key.append(i);
                key.append(" ");
                key.append(j);
                map.put(key.toString(), 1);
                counter++;
            }
        }
    }
}
