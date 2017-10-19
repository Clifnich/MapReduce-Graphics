package com.puzhen.chord.consistenthashing;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

public class DistributedHashTable {

    private static final int m = 3;
    private List<Bucket> buckets = new ArrayList<>((int) Math.pow(2, m));
    private String[] initialServerPorts = {"8083", "8081", "8082"};
    private static final Logger logger = Logger.getLogger(DistributedHashTable.class);
    private Map<String, Integer> portNumber2IndexMap = new HashMap<>();
    private static DistributedHashTable instance;

    public static DistributedHashTable getInstance() {
        if (instance == null)
            instance = new DistributedHashTable();
        return instance;
    }

    private DistributedHashTable() {

        configureServers();
        logger.info("Dismissing all slaves");
        for (String portNumber : initialServerPorts) {
            dismissSlave(portNumber);
        }
    }

    /**
     * add 2^m default buckets to the list
     */
    private void renewBuckets() {
        buckets = new ArrayList<>((int) Math.pow(2, m));
        for (int i = 0; i < (int) Math.pow(2, m); i++)
            buckets.add(new Bucket());
    }

    /**
     * This method hashes the server port number into the buckets
     */
    private void configureServers() {
        // renew the buckets
        renewBuckets();
        logger.info("Configuring servers.., buckets size is: " + buckets.size() + "\n\n");
        for (String port : initialServerPorts) {
            addOneSlave(port);
        }
        logger.info("After configuring, portNumber2IndexMap's size is: " + portNumber2IndexMap.size());
    }

    /**
     * Add one slave(represented by portNumber) to the buckets and portNumber2IndexMap
     * @param portNumber
     */
    private void addOneSlave(String portNumber) {
        int bucketPosition = getBucketPosition(portNumber);
        Bucket bucket = buckets.get(bucketPosition);
        bucket.getServers().add(portNumber);
        portNumber2IndexMap.put(portNumber, bucketPosition);
        logger.info("port [" + portNumber + "] is hashed to bucket " + bucketPosition);
    }

    /**
     * The actual hash function lies here.
     * @param key
     * @return the bucket position for a key after hashing with SHA-1 and mod by 2^3
     */
    public static int getBucketPosition(String key) {
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

    public String getSuccessingServer(String portNumber) {
        int serverPosition = 0;
//        logger.info("In getSuccessingServer(), portNumber is: [" + portNumber + "].");
        try {
//            if (!portNumber2IndexMap.containsKey(portNumber)) {
//                logger.error("no such key!!!!!!!!!!!!!!!!! and size is " + portNumber2IndexMap.size());
//            }
            serverPosition = portNumber2IndexMap.get(portNumber);
        } catch (NullPointerException e) {
            e.printStackTrace();
            if (portNumber2IndexMap == null) {
                logger.info("Yes, the map is null");
            } else {
                logger.info(portNumber2IndexMap.toString());
            }
        }
        return getSuccessorServerPort(serverPosition + 1);
    }

    public void dismissSlave(String portNumber) {
        Bucket bucket = buckets.get(portNumber2IndexMap.get(portNumber));
        // assume that each server queue has only one server
        bucket.getServers().poll();
        portNumber2IndexMap.remove(portNumber);
    }

    public void addSlave(String portNumber) {
        addOneSlave(portNumber);
    }

    public int size() {
        // TODO fill this up
        return 0;
    }

}
