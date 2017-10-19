package com.puzhen.chord.distributedhashmap;

import com.puzhen.chord.consistenthashing.DistributedHashTable;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BasicTest {

    static DistributedHashTable hashTable;

    @BeforeClass
    public static void initTable() {
        hashTable = DistributedHashTable.getInstance();
    }

    @Test
    public void testGettingSuccessingServer0() {
        assertEquals("8082", hashTable.getSuccessingServer("8081"));
    }

    @Test
    public void test1() {
        assertEquals("8083", hashTable.getSuccessingServer("8082"));
    }

    @Test
    public void test2() {
        assertEquals("8081", hashTable.getSuccessingServer("8083"));
    }

}
