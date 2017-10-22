package com.puzhen.visionstorage.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class TenPutNGet {

    public static void main(String[] args) {
        System.out.println("Going to put ten values and get all of them out of HBase");
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path("hbase-site.xml"));
        try {
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
            Admin admin = connection.getAdmin();
            HTableDescriptor desc = new HTableDescriptor(tableTen);
            desc.addFamily(new HColumnDescriptor(family));
            admin.createTable(desc);

            Table table = connection.getTable(tableTen);

            // put 10 times
//            for (int i = 0; i < 10; i ++) {
//                Put p = new Put(Bytes.toBytes(keys[i]));
//                p.addImmutable(family, qualifier, Bytes.toBytes(values[i]));
//                table.put(p);
//            }

            // get 10 times
            for (int i = 0; i < 10; i++) {
                Get get = new Get(Bytes.toBytes("key0"));
                Result r = table.get(get);
                byte[] value = r.getValue(family, qualifier);
                System.out.println("Value for key " + keys[i] + " is: " + Bytes.toString(value));
            }

            table.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TableName tableTen = TableName.valueOf("image3");
    private static byte[] family = Bytes.toBytes("family");
    private static byte[] qualifier = Bytes.toBytes("qualifier");

    private static String[] keys = {"key1", "key2", "key3",
            "key4", "key5", "key6", "key7", "key8", "key9", "key10"};

    private static String[] values = {"value1", "value2", "value3",
            "value4", "value5", "value6", "value7", "value8", "value9", "value10"};
}
