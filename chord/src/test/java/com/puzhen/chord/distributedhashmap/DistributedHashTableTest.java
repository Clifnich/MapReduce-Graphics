package com.puzhen.chord.distributedhashmap;

import com.puzhen.chord.consistenthashing.DistributedHashTable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistributedHashTableTest {

//    @BeforeClass
//    public static void startServer() {
//        String[] args0 = {"8080"};
//        SlaveServer.main(args0);
//
//        String[] args1 = {"8081"};
//        SlaveServer.main(args1);
//
//        String[] args2 = {"8082"};
//        SlaveServer.main(args2);
//    }

    @Test
    public void test0() {
        DistributedHashTable distributedHashTable = new DistributedHashTable();
        distributedHashTable.put("tom", "cat");
        assertEquals("cat", distributedHashTable.get("tom"));

        distributedHashTable.put("jetty", "mouse");
        assertEquals("mouse", distributedHashTable.get("jetty"));

        distributedHashTable.put("apache", "wildling");
        assertEquals("wildling", distributedHashTable.get("apache"));

        distributedHashTable.put("8081", "human");
        assertEquals("human", distributedHashTable.get("8081"));
    }
}
