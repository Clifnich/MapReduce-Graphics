package com.puzhen.visionstorage.test;

import com.puzhen.visionstorage.main.ImageProcessBO;

public class MyToIntTest {

    public static void main(String[] args) {
        byte[] bytes = {51, 55};
        System.out.println("should be 37: " + ImageProcessBO.myToInt(bytes));
    }
}
