package com.stackverflow.nullpointer;

import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkClass {

    public static void main(String[] args) {
        SparkConf conf = new SparkConf();
        conf.setMaster("local[*]").setAppName("java 9 example");
        SparkSession session = SparkSession.builder().config(conf).getOrCreate();
        Dataset<Row> ds = session.read().text("/Users/naman.nigam/GitHub/Naman/stackoverflow-nullpointer/stackoverflow/mockito/foo.txt");
        System.out.println(ds.count());
    }
}