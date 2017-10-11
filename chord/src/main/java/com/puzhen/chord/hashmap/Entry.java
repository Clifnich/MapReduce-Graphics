package com.puzhen.chord.hashmap;

public class Entry {

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public Entry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public Entry() {}

    public String getValue() {
        return value;

    }

    public void setValue(String value) {
        this.value = value;
    }
}
