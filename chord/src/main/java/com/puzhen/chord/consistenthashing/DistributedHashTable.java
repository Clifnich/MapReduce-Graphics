package com.puzhen.chord.consistenthashing;

import java.util.*;

public class DistributedHashTable {

    private static final int m = 3;
    private List<Bucket> buckets = new ArrayList<Bucket>((int) Math.pow(2, m));

    public DistributedHashTable() {
        configureServers();
    }

    /**
     * This method hashes the server port number into the buckets
     */
    private void configureServers() {
        // TODO fill this up
    }

    public void put(String key, String value) {
        // TODO fill this up
    }

    public String get(String key) {
        // TODO fill this up
        return "not found";
    }

    public int size() {
        // TODO fill this up
        return 0;
    }

}
