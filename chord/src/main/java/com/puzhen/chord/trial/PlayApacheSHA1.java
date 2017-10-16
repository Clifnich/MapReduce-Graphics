package com.puzhen.chord.trial;

import org.apache.commons.codec.digest.DigestUtils;

public class PlayApacheSHA1 {

    public static void main(String[] args) {
        String sha = DigestUtils.sha1Hex("tom");
        byte[] bytes = DigestUtils.sha1("tom");
        System.out.println(sha);
        System.out.println("Here is the byte array:");
        int sum = 0;
        for (byte b : bytes) {
            System.out.println(b);
            sum += b;
        }
        System.out.println("Sum is: " + sum);
    }
}
