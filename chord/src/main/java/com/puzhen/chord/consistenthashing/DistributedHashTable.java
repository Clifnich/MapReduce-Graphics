package com.puzhen.chord.consistenthashing;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.nio.Buffer;
import java.security.MessageDigest;
import java.util.*;

public class DistributedHashTable {

    private static final int m = 3;
    private List<Bucket> buckets = new ArrayList<Bucket>((int) Math.pow(2, m));
    private String[] serverPorts = {"8080", "8081", "8082"};
    private static final Logger logger = Logger.getLogger(DistributedHashTable.class);

    public DistributedHashTable() {
        // add 2^m default buckets to the list
        for (int i = 0; i < (int) Math.pow(2, m); i++)
            buckets.add(new Bucket());
        configureServers();
    }

    /**
     * This method hashes the server port number into the buckets
     */
    private void configureServers() {
        for (String port : serverPorts) {
            int bucketPosition = getBucketPosition(port);
            Bucket bucket = buckets.get(bucketPosition);
            bucket.getServers().add(port);
            logger.info("port " + port + " is hashed to bucket " + bucketPosition);
        }
    }

    /**
     * The actual hash function lies here.
     * @param key
     * @return the bucket position for a key after hashing with SHA-1 and mod by 2^3
     */
    private int getBucketPosition(String key) {
        // use BKDR hash function
        long sum = 0;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            sum = sum * 131 + c;
        }
        return (int) (sum % (int) Math.pow(2, m));
    }
//    private int getBucketPosition(String key) {
//        // use double hashing
//        byte[] bytes;
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            byte[] bytesOfMessage = key.getBytes();
//            bytes = messageDigest.digest(bytesOfMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//            // if has exception, use SHA-1 to HASH
//            bytes = DigestUtils.sha1(key);
//        }
//        String newKey = String.valueOf(bytes);
//        bytes = DigestUtils.sha1(newKey);
//        int sum = 0;
//        for (int i = 0; i < bytes.length; i++)
//            sum += bytes[i];
//        return Math.abs(sum) % (int) Math.pow(2, m);
//    }

    /**
     * @param keyPosition
     * @return the port of the successor server of the given key position
     */
    private String getSuccessorServerPort(int keyPosition) {
        for (; keyPosition < buckets.size(); keyPosition++) {
            Bucket bucket = buckets.get(keyPosition);
            if (bucket.hasServer())
                return bucket.getServers().peek();
            // make sure that the search can wrap around
            if (keyPosition == buckets.size() - 1)
                keyPosition = -1;
        }
        // even though it is not likely to happen, if the for loop fails
        // to find the server, use port 8080 as default
        return "8080";
    }

    public void put(String key, String value) {
        int keyPosition = getBucketPosition(key);
        logger.info("key position for key " + key + " is " + keyPosition);
        String port = getSuccessorServerPort(keyPosition);
        String urlString = "http://localhost:" + port + "/put?key=" + key + "&value=" + value;
        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL(urlString)).openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = rd.readLine();
            rd.close();
            logger.info("Putting key-value pair (" + key + ", " + value + ") to server "
                    + port + ", server resonse is: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String key) {
        int keyPosition = getBucketPosition(key);
        String port = getSuccessorServerPort(keyPosition);
        String urlString = "http://localhost:" + port + "/get?key=" + key;
        try {
            HttpURLConnection conn = (HttpURLConnection) (new URL(urlString)).openConnection();
            conn.setDoInput(true);
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String response = rd.readLine();
            if (response.equals("null"))
                return null;
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int size() {
        // TODO fill this up
        return 0;
    }

}
