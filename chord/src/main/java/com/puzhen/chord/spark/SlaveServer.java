package com.puzhen.chord.spark;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class SlaveServer {

    static final Logger logger = Logger.getLogger(SlaveServer.class);
    static String masterPort;
    static String thisPort;
    static Map<String, String> map;

    /**
     * args[0]: port number of the master node
     * args[1]: port number for this slave
     * @param args
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Please enter the port number and master port in parameter list.");
            return;
        }
        masterPort = args[0];
        thisPort = args[1];
        port(Integer.valueOf(thisPort));
        map = new HashMap<>();

        get("/get", (req, res) -> {
            String key = req.queryParams("key");
            logger.info("GET request, key here is [" + key + "].");
            String value = map.get(key);
            if (value == null)
                return "null";
            else
                return value;
        });

        put("/put", (req, res) -> {
            String key = req.queryParams("key");
            logger.info("PUT request, key here is [" + key + "].");
            String value = req.queryParams("value");
            map.put(key, value);
            return "Done";
        });

        post("/leave", (req, res) -> {
            try {
                HttpURLConnection conn = (HttpURLConnection) (new URL(
                        "http://localhost:" + masterPort +
                                "/chord/requestForLeave?portNumber=" + thisPort)).openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = rd.readLine();
                if (response.startsWith("OK")) {
                    logger.info("Master's response: " + response);
                    String port = response.split(" ")[1];
                    logger.info("Moving all keys to server " + port);
                    moveAllKeys(port);
                    logger.info("Master has granted my leave, leaving now...");
                    System.exit(0);
                }
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        });

        // This sanityCheck checks if the slave can connect to master
        post("/sanityCheck", (req, res) -> {
           try {
               HttpURLConnection conn = (HttpURLConnection) (new URL(
                       "http://localhost:" + masterPort + "/chord/hello")).openConnection();
               conn.setDoInput(true);
               BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
               String response = rd.readLine();
               return "Master's response is: [" + response + "].";
           } catch (Exception e) {
               e.printStackTrace();
               return "Error";
           }
        });
    }

    private static void moveAllKeys(String portNumber) {
        for (String key : map.keySet()) {
            String value = map.get(key);
            logger.info("Moving (" + key + ", " + value + ")...");
            String urlString = "http://localhost:" + portNumber + "/put?key=" + key + "&value=" + value;
            try {
                HttpURLConnection conn = (HttpURLConnection) (new URL(urlString)).openConnection();
                conn.setRequestMethod("PUT");
                conn.setDoInput(true);
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                logger.info(rd.readLine());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
