package com.puzhen.visionstorage.trial;

import org.apache.hadoop.hbase.util.Bytes;

public class PlayBytes {

    public static void main(String[] args) {
        String hi = "hi";
        System.out.println(Bytes.toBytes(hi));
        System.out.println(Bytes.toBytes(hi));

    }
}
