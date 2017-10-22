package com.puzhen.visionstorage.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseScan {

    public static void main(String[] args) {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path("hbase-site.xml"));
        try {
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
            Table table = connection.getTable(table1);

            byte[] row1 = Bytes.toBytes("row1");
            Put p = new Put(row1);
            p.addImmutable(family1.getBytes(), Bytes.toBytes("qualifier1"), Bytes.toBytes("cell_data"));
            table.put(p);

            Scan scan = new Scan();
            scan.addColumn(family1.getBytes(), Bytes.toBytes("qualifer1"));
            ResultScanner resultScanner = table.getScanner(scan);
            for (Result result : resultScanner) {
                System.out.println("Found row: " + result);
            }
            resultScanner.close();
            table.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TableName table1 = TableName.valueOf("Table2");
    private static String family1 = "Family1";
    private static String family2 = "Family2";
}
