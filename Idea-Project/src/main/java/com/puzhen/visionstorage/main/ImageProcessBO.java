package com.puzhen.visionstorage.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class ImageProcessBO {

    public static boolean createDataFile(int n, int m, String filepath) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filepath)));
            for (int i = 0; i < n; i++) {
                if (i != 0) {
                    writer.write("\n");
                }
                for (int j = 0; j < m; j++) {
                    if (j != 0) {
                        writer.write(" ");
                    }
                    Random random = new Random();
                    writer.write(String.valueOf(random.nextInt(255)));
                    writer.write(",");
                    writer.write(String.valueOf(random.nextInt(255)));
                    writer.write(",");
                    writer.write(String.valueOf(random.nextInt(255)));
                }
            }
            writer.flush();
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean process(Image image) {
        List<SimplePixel> simplePixelList = image.getPixelList();
        int size = simplePixelList.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                SimplePixel pixel1 = simplePixelList.get(i);
                SimplePixel pixel2 = simplePixelList.get(j);
                String key = String.valueOf(i) + " " + String.valueOf(j);
                int value = getDistance(pixel1, pixel2);

                // put a key-value pair into HBase
                Put put = new Put(myToBytes(key));
                put.addImmutable(family, qualifier, myToBytes(String.valueOf(value)));
                try {
                    table.put(put);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("Just store (" + key + ", " + value + ") to HBase.");
            }
        }
        System.out.println("Finish processing an image.");
        return true;
    }

    private int getDistance(SimplePixel pixel1, SimplePixel pixel2) {
        double sum = 0;
        sum += Math.pow(pixel1.getValue1() - pixel2.getValue1(), 2);
        sum += Math.pow(pixel1.getValue2() - pixel2.getValue2(), 2);
        sum += Math.pow(pixel1.getValue3() - pixel2.getValue3(), 2);
        return (int) (Math.sqrt(sum) + 0.5);
    }

    private void setUp() {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path("hbase-site.xml"));
        try {
            HBaseAdmin.checkHBaseAvailable(config);
            connection = ConnectionFactory.createConnection(config);
            table = connection.getTable(tableName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            table.close();
            connection.close();
            System.out.println("ImageProcessBO is shutdown, bye~");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getDistance(String key) {
        Get get = new Get(myToBytes(key));
        try {
            Result r = table.get(get);
            byte[] value = r.getValue(family, qualifier);
            return myToInt(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private ImageProcessBO() {
        setUp();
    }

    public static ImageProcessBO getInstance() {
        if (instance == null)
            instance = new ImageProcessBO();
        return instance;
    }

    /**
     * Use my own way to encrpyt key while encrpyting value still depends on Bytes library
     * @param str
     * @return
     */
    public static byte[] myToBytes(String str) {
        byte[] result = new byte[str.length()];
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            result[i] = (byte) c;
        }
        return result;
    }

    public static int myToInt(byte[] bytes) {
        int length = bytes.length;
        double result = 0;
        for (int i = 0; i < length; i++) {
            result += (bytes[i] - '0') * Math.pow(10, length - i - 1);
        }
        return (int) (result + 0.5);
    }

    private static ImageProcessBO instance;
    private Connection connection;
    private Table table;
    private static final TableName tableName = TableName.valueOf("image2");
    private static byte[] family = Bytes.toBytes("family");
    private static byte[] qualifier = Bytes.toBytes("qualifier");
}
