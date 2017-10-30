package com.puzhen.visionstorage.script;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import static org.junit.Assert.assertEquals;

public class PutGetTest {

    public static void main(String[] args) {
        System.out.println("Put and get from a HBASE table..");
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path("hbase-site.xml"));
        try {
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
            Table table = connection.getTable(tableTen);
            Put p = new Put(Bytes.toBytes("key1"));
            p.addImmutable(family, qualifier, Bytes.toBytes("value1"));
            table.put(p);

            Get get = new Get(Bytes.toBytes("key1"));
            Result r = table.get(get);
            byte[] value = r.getValue(family, qualifier);
            assertEquals("value1", Bytes.toString(value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TableName tableTen = TableName.valueOf("table1");
    private static byte[] family = Bytes.toBytes("family");
    private static byte[] qualifier = Bytes.toBytes("qualifier");
}
