package com.puzhen.visionstorage.test;

import com.puzhen.visionstorage.main.ImageProcessBO;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateDataTest {

    static final String DATA_PATH = "test-data/image.txt";

    @Test
    public void test0() {
        assertTrue(ImageProcessBO.createDataFile(3, 3, DATA_PATH));
        try {
            BufferedReader rd = new BufferedReader(new FileReader(new File(DATA_PATH)));
            String line = rd.readLine();
            assertEquals(3, line.split(" ").length);
            assertEquals(3, rd.readLine().split(" ").length);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Exception is thrown");
        }
    }
}
