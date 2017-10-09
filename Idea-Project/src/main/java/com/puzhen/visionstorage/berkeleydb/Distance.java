package com.puzhen.visionstorage.berkeleydb;

import com.sleepycat.persist.model.Entity;
import com.sleepycat.persist.model.PrimaryKey;

@Entity
public class Distance {

    @PrimaryKey
    private String key;

    private int distance;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Distance() {}

    public Distance(String key, int distance) {
        this.key = key;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "key='" + key + '\'' +
                ", distance=" + distance +
                '}';
    }
}
