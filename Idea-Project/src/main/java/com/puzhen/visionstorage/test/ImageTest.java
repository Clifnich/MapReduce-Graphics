package com.puzhen.visionstorage.test;

import com.puzhen.visionstorage.main.Image;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImageTest {

    @Test
    public void test0() {
        Image image = Image.createImageFromFile("test-data/50x50.txt");
        assertEquals(50, image.getM());
        assertEquals(50, image.getN());
        assertEquals(2500, image.getPixelList().size());
    }
}
