package com.puzhen.chord.hashmap;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.LinkedList;

public class MyHashMap {

    public void put(String key, String value) {
        slots[getSlotPosition(key)].add(new Entry(key, value));
        numberOfKeys++;
    }

    public String get(String key) {
        LinkedList<Entry> list = slots[getSlotPosition(key)];
        if (list.size() == 1)
            return list.get(0).getValue();
        for (Entry entry : list) {
            if (entry.getKey().equals(key))
                return entry.getValue();
        }
        return null;
    }

    public int size() {
        return numberOfKeys;
    }

    public MyHashMap() {
        // initialize the array and each LinkedList
        slots = new LinkedList[SIZE];
        for (int i = 0; i < SIZE; i++) {
            slots[i] = new LinkedList<>();
        }
    }

    private int getSlotPosition(String key) {
        byte[] bytes = DigestUtils.sha1(key);
        int sum = 0;
        for (int i = 0; i < bytes.length; i++)
            sum += bytes[i];
        return sum % SIZE;
    }

//    private LinkedList
    private LinkedList<Entry>[] slots;
    private int numberOfKeys;

    private static final int SIZE = 10;
}
