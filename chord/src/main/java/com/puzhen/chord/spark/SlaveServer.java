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

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please enter the port number in parameter list.");
            return;
        }
        port(Integer.valueOf(args[0]));

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
                        "http://localhost:8080/chord/requestForLeave?portNumber=1234")).openConnection();
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String response = rd.readLine();
                return response;
            } catch (Exception e) {
                e.printStackTrace();
                return "Error";
            }
        });
    }

    static Map<String, String> map;
}
