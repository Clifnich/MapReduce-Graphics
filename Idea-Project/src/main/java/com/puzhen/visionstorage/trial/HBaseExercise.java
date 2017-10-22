package com.puzhen.visionstorage.trial;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseExercise {

//    public static Configuration configuration;
//    static {
//        configuration = HBaseConfiguration.create();
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");
//        configuration.set("hbase.zookeeper.quorum", "192.168.1.100");
//        configuration.set("hbase.master", "192.168.1.100:600000");
//    }

    public static void main(String[] args) {
        //createTable("puzhen");
        Configuration config = HBaseConfiguration.create();
//        String path = object.getClass()
//                .getClassLoader()
//                .getResource("hbase-site.xml")
//                .getPath();
        config.addResource(new Path("hbase-site.xml"));
        try {
            HBaseAdmin.checkHBaseAvailable(config);
            Connection connection = ConnectionFactory.createConnection(config);
//            Admin admin = connection.getAdmin();
//
//            HTableDescriptor desc = new HTableDescriptor(table1);
//            desc.addFamily(new HColumnDescriptor(family1));
//            desc.addFamily(new HColumnDescriptor(family2));
//            admin.createTable(desc);

            byte[] row1 = Bytes.toBytes("row1");
            Put p = new Put(row1);
            p.addImmutable(family1.getBytes(), Bytes.toBytes("qualifier1"), Bytes.toBytes("cell_data"));
            Table table = connection.getTable(table1);
            table.put(p);

            Get g = new Get(row1);
            Result r = table.get(g);
            byte[] value = r.getValue(family1.getBytes(), Bytes.toBytes("qualifier1"));
            System.out.println("Previously inserted: " + Bytes.toString(value));
            table.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private static String transferBytesToString(byte[] bytes) {
//        StringBuffer sb = new StringBuffer();
//        for (int i = 0; i < bytes.length; i++)
//            sb.append(bytes[i]);
//        return sb.toString();
//    }

    private static TableName table1 = TableName.valueOf("Table2");
    private static String family1 = "Family1";
    private static String family2 = "Family2";

//    private static void createTable(String tableName) {
//        System.out.println("start creating table ...");
//        try {
//            HBaseAdmin hBaseAdmin = new HBaseAdmin(configuration);
//            if (hBaseAdmin.tableExists(tableName)) {
//                hBaseAdmin.disableTable(tableName);
//                hBaseAdmin.deleteTable(tableName);
//                System.out.println(tableName + " exists, deleting it.");
//            }
//            HTableDescriptor tableDescriptor = new HTableDescriptor(tableName);
//            tableDescriptor.addFamily(new HColumnDescriptor("column1"));
//            tableDescriptor.addFamily(new HColumnDescriptor("column2"));
//            tableDescriptor.addFamily(new HColumnDescriptor("column3"));
//            hBaseAdmin.createTable(tableDescriptor);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("end create table");
//    }
}
