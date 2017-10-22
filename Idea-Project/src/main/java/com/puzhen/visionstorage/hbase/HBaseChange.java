package com.puzhen.visionstorage.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseChange {

    public static void main(String[] args) {
        Configuration config = HBaseConfiguration.create();
        config.addResource(new Path("hbase-site.xml"));
        try {
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
            Table table = connection.getTable(table1);

            byte[] row1 = Bytes.toBytes("row1");
            byte[] qualifier1 = Bytes.toBytes("qualifier1");
            Get g = new Get(row1);
            Result r = table.get(g);
            byte[] value = r.getValue(family1.getBytes(), qualifier1);
            System.out.println("Previously inserted: " + Bytes.toString(value));

            Delete delete = new Delete(row1);
            delete.addColumn(family1.getBytes(), qualifier1);
            table.delete(delete);

            g = new Get(row1);
            r = table.get(g);
            byte[] value2 = r.getValue(family1.getBytes(), qualifier1);
            System.out.println("After deletion [" + Bytes.toString(value2) + "].");

            Put p = new Put(row1);
            p.addImmutable(family1.getBytes(), Bytes.toBytes("qualifier1"), Bytes.toBytes("cell_data"));
            table.put(p);

            g = new Get(row1);
            r = table.get(g);
            value2 = r.getValue(family1.getBytes(), qualifier1);
            System.out.println("Put again [" + Bytes.toString(value2) + "].");
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
