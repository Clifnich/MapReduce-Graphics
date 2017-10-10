package com.puzhen.visionstorage.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MemoryStorage {

    private static final int NUMBER_OF_KEYS = 400000;

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i < NUMBER_OF_KEYS; i++) {
            map.put(String.valueOf(i), 0);
            System.out.println("Putting key " + i);
        }
        String str = (new Scanner(System.in)).nextLine();
        System.out.println("You just entered: " + str);
    }
}
