package com.puzhen.chord.hashmap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MyHashMapTest {

    static MyHashMap map;

    @Test
    public void testPut() {
        map = new MyHashMap();
        map.put("name", "tom");
        assertEquals("tom", map.get("name"));
    }

    @Test
    public void testSize() {
        map = new MyHashMap();
        map.put("name", "tom");
        assertEquals(1, map.size());
    }
}
