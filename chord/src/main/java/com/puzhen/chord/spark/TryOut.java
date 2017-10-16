package com.puzhen.chord.spark;

import static spark.Spark.*;

public class TryOut {

    public static void main(String[] args) {
        port(8080);
        get("/hello", (req, res) -> "Hello World");
    }
}
