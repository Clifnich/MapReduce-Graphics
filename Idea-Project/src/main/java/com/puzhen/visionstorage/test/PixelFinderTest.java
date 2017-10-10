package com.puzhen.visionstorage.test;

import com.puzhen.visionstorage.main.Pixel;
import com.puzhen.visionstorage.main.PixelFinder;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;

public class PixelFinderTest extends TestCase{

    public PixelFinderTest(String name) {
        super(name);
    }

    public void testOneDimension0() {
        try {
            Pixel pixel = PixelFinder.find(1, 1, "test-data" + File.separator + "tiny.txt");
            int[] content = pixel.getContents();
            assertEquals(1, content.length);
            assertEquals(1, content[0]);
        } catch (IOException e) {
            e.printStackTrace();
            fail("");
        }
    }

    public void testOneDimension1() {
        try {
            Pixel pixel = PixelFinder.find(2, 2, "test-data" + File.separator + "tiny.txt");
            int[] content = pixel.getContents();
            assertEquals(1, content.length);
            assertEquals(5, content[0]);
        } catch (IOException e) {
            e.printStackTrace();
            fail("");
        }
    }

    public void testOneDimension2() {
        try {
            Pixel pixel = PixelFinder.find(3, 2, "test-data" + File.separator + "tiny.txt");
            int[] content = pixel.getContents();
            assertEquals(1, content.length);
            assertEquals(8, content[0]);
        } catch (IOException e) {
            e.printStackTrace();
            fail("");
        }
    }

    public void testTwoDimension0() {
        try {
            Pixel pixel = PixelFinder.find(1, 1, "test-data" + File.separator + "two-dimensions.txt");
            int[] content = pixel.getContents();
            assertEquals(2, content.length);
            assertEquals(1, content[0]);
            assertEquals(1, content[1]);
        } catch (IOException e) {
            e.printStackTrace();
            fail("");
        }
    }

    public void testTwoDimension1() {
        try {
            Pixel pixel = PixelFinder.find(2, 2, "test-data" + File.separator + "two-dimensions.txt");
            int[] content = pixel.getContents();
            assertEquals(2, content.length);
            assertEquals(5, content[0]);
            assertEquals(34, content[1]);
        } catch (IOException e) {
            e.printStackTrace();
            fail("");
        }
    }
}
