package com.puzhen.visionstorage.test;

import com.puzhen.visionstorage.main.DistanceStore;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class Image30x30Test {

    static DistanceStore distanceStore = DistanceStore.getInstance();

    @BeforeClass
    public static void configure() {
        Date time1 = new Date();
        try {
            distanceStore.configure("test-data" + File.separator + "30x30");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Date time2 = new Date();
        System.out.println((time2.getTime() - time1.getTime()) / 1000 + "s.");
    }

    @Test
    public void test0() {
        int distance = distanceStore.getDistance(1,1,2,2);
        assertEquals(43, distance);
    }

    @Test
    public void test1() {
        int distance = distanceStore.getDistance(4,1,9,2);
        assertEquals(75, distance);
    }

    @Test
    public void test2() {
        int distance = distanceStore.getDistance(4,3,9,4);
        assertEquals(92, distance);
    }

    @Test
    public void test3() {
        int distance = distanceStore.getDistance(10,3,12,4);
        assertEquals(62, distance);
    }

    @Test
    public void test4() {
        int distance = distanceStore.getDistance(10,4,12,1);
        assertEquals(63, distance);
    }

    @Test
    public void test5() {
        int distance = distanceStore.getDistance(1,30,2,29);
        assertEquals(72, distance);
    }

    @Test
    public void test6() {
        int distance = distanceStore.getDistance(4,30,9,29);
        assertEquals(59, distance);
    }

    @Test
    public void test7() {
        int distance = distanceStore.getDistance(30,3,29,4);
        assertEquals(105, distance);
    }

    @Test
    public void test8() {
        int distance = distanceStore.getDistance(10,30,12,4);
        assertEquals(21, distance);
    }

    @Test
    public void test9() {
        int distance = distanceStore.getDistance(30,4,29,1);
        assertEquals(36, distance);
    }
}
