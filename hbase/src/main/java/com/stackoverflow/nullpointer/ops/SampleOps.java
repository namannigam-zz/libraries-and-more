package com.stackoverflow.nullpointer.ops;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SampleOps {


    private static void everythingInOne() throws IOException {
//        Configuration config = HBaseConfiguration.create();
//        config.set(HConstants.ZOOKEEPER_QUORUM, "10.34.181.221");
//        config.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2181");
//        config.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase-unsecure");
//
//        // String table, String ... columnFamilies)
//        TableName tableName = TableName.valueOf("game");
//        String family1 = "a";
//
//        // create connection
//        Connection connection = ConnectionFactory.createConnection(config);
//        Admin admin = connection.getAdmin();
//
//        // create table and column-family
//        byte[] ourOnlyTable = Bytes.toBytes("game");
//        byte[] ourOnlyColumnFamily = Bytes.toBytes("a");

//        ColumnFamilyDescriptor ourOnlyColumnFamilyDescription =
//                ColumnFamilyDescriptorBuilder.newBuilder(ourOnlyColumnFamily).build();
//
//        TableDescriptor ourOnlyTableDescription =
//                TableDescriptorBuilder.newBuilder(tableName).setColumnFamily(ourOnlyColumnFamilyDescription).build();
//
//        admin.createTable(ourOnlyTableDescription); // once
//
//        // put operation
//        byte[] rowKey = Bytes.toBytes("row-01"); // replace the constant by row name
//        byte[] columnQualifier = Bytes.toBytes("qualifier-01"); //questionIndex
//        byte[] columnValue = Bytes.toBytes(4); // score
//        Put put = new Put(rowKey);
//        put.addColumn(ourOnlyColumnFamily, columnQualifier, columnValue);
//        connection.getTable(tableName).put(put);
//
//        // get operation
//        Get get = new Get(rowKey);
//        Result result = connection.getTable(tableName).get(get);
//
//        //scan
//        Scan scan = new Scan().withStartRow(Bytes.toBytes(0x0)).withStopRow(Bytes.toBytes(0x7fffffff));
//        scan.addColumn(ourOnlyColumnFamily, columnQualifier); // not reuired for us
//
//        // filters (not required currently)
//        Filter filter1 = new PrefixFilter(rowKey);
//        Filter filter2 =
//                new QualifierFilter(CompareFilter.CompareOp.GREATER_OR_EQUAL, new BinaryComparator(columnQualifier));
//        List<Filter> filters = Arrays.asList(filter1, filter2);
//
//        // scan with filters
//        scan.setFilter(new FilterList(FilterList.Operator.MUST_PASS_ALL, filters));
//        try (ResultScanner scanner = connection.getTable(tableName).getScanner(scan)) {
//            for (Result res : scanner) {
//                System.out.println("Found row: " + res);
//            }
//        }
//
//        // delete row
//        Delete delete = new Delete(rowKey);
//        delete.addColumn(ourOnlyColumnFamily, columnQualifier);
//        connection.getTable(tableName).delete(delete);
//

    }
}