package com.puzhen.visionstorage.trial;

import java.util.Date;

public class TryTimeSpan {

    public static void main(String[] args) throws InterruptedException{
        Date time1 = new Date();
        Thread.sleep(1000);
        Date time2 = new Date();
        System.out.println((time2.getTime() - time1.getTime()) / 1000 + "s.");
    }
}
