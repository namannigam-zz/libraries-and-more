package com.stackoverflow.nullpointer.client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;
import java.util.logging.Logger;

public class HBaseClient {

    private static Logger logger = Logger.getLogger(HBaseClient.class.getName());
    private static Connection connection;

//    static {
//        logger.info("Trying to connect...");
//        try {
//            connection = ConnectionFactory.createConnection(getConfiguration());
//            if (!connection.getAdmin().tableExists(getTableName())) {
//                createTable();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        logger.info("Connection created successfully!");
//    }

    private static void createTable() throws IOException {
//        2.0.1 impl
//        ColumnFamilyDescriptor ourOnlyColumnFamilyDescription =
//                ColumnFamilyDescriptorBuilder.newBuilder(ourOnlyColumnFamily).build();
//
//        TableDescriptor ourOnlyTableDescription =
//                TableDescriptorBuilder.newBuilder(tableName).setColumnFamily(ourOnlyColumnFamilyDescription).build();

        HTableDescriptor hTableDescriptor = new HTableDescriptor(getTableName());
        HColumnDescriptor hColumnDescriptor = new HColumnDescriptor("a".getBytes());
        hTableDescriptor.addFamily(hColumnDescriptor);
        connection.getAdmin().createTable(hTableDescriptor);
    }

    public static Connection getConnection() {
        return connection;
    }

    public static Configuration getConfiguration() {
        Configuration config = HBaseConfiguration.create();
        config.set(HConstants.ZOOKEEPER_QUORUM, "10.32.58.133");
        config.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2181");
        config.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase-unsecure");
        config.set(HConstants.HBASE_CLIENT_IPC_POOL_SIZE, "");
        return config;
    }

    public static TableName getTableName() {
        return TableName.valueOf("game");
    }
}