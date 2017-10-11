package com.puzhen.visionstorage.test;

import com.puzhen.visionstorage.main.DistanceStore;
import org.junit.*;
import static org.junit.Assert.*;
import java.io.*;

public class DistanceStoreTest {

    static DistanceStore distanceStore = DistanceStore.getInstance();

//    public DistanceStoreTest(String name) {
//        super(name);
//        System.out.println("Does it run 3 times?");
//        try {
//            distanceStore.configure("test-data" + File.separator + "tiny.txt");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            fail("data file not found");
//        }
//    }

    @BeforeClass
    public static void configure() {
        System.out.println("This should only run once");
        try {
            distanceStore.configure("test-data" + File.separator + "tiny.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fail("data file not found");
        }
    }

    @Test
    public void test0() {
        assertEquals(4, distanceStore.getDistance(1, 1, 2, 2));
    }

    @Test
    public void test1() {
        assertEquals(4, distanceStore.getDistance(1, 2, 2, 3));
    }

    @Test
    public void test2() {
        assertEquals(5, distanceStore.getDistance(2, 1, 3, 3));
    }
}
