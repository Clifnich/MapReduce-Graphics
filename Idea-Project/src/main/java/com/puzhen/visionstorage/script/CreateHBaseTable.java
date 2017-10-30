package com.puzhen.visionstorage.script;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class CreateHBaseTable {

    public static void main(String[] args) {
        System.out.println("Going to create a HBase table...");
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
            table.close();
            connection.close();
            System.out.println("Successfully created a table..");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TableName tableTen = TableName.valueOf("table1");
    private static byte[] family = Bytes.toBytes("family");
    //private static byte[] qualifier = Bytes.toBytes("qualifier");
}
