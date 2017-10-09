package com.puzhen.visionstorage.trial;

public class TrySplitByComma {

    public static void main(String[] args) {
        String str = "123,123";
        String[] elements = str.split(",");
        System.out.println(elements[1]);
    }
}
