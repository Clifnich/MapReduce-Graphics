package com.puzhen.chord.consistenthashing;

import com.puzhen.chord.hashmap.Entry;

import java.util.LinkedList;
import java.util.Queue;

public class Bucket {

    private Queue<Entry> entries;
    private Queue<String> servers;

    public Bucket() {
        entries = new LinkedList<>();
        servers = new LinkedList<>();
    }

    /**
     * @return if there is a server residing in this bucket
     */
    public boolean hasServer() {
        return servers.size() != 0;
    }

    public Queue<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Queue<Entry> entries) {
        this.entries = entries;
    }

    public Queue<String> getServers() {
        return servers;
    }

    public void setServers(Queue<String> servers) {
        this.servers = servers;
    }
}
