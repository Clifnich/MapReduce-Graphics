package com.puzhen.visionstorage.test;

import com.puzhen.visionstorage.main.DistanceStore;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;

public class DistanceStoreTest extends TestCase {

    DistanceStore distanceStore = DistanceStore.getInstance();

    public DistanceStoreTest(String name) {
        super(name);
        try {
            distanceStore.configure("test-data" + File.separator + "tiny.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("data file not found");
        }
    }

    public void test0() {
        assertEquals(4, distanceStore.getDistance(1, 1, 2, 2));
    }

    public void test1() {
        assertEquals(4, distanceStore.getDistance(1, 2, 2, 3));
    }

    public void test2() {
        assertEquals(5, distanceStore.getDistance(2, 1, 3, 3));
    }
}
