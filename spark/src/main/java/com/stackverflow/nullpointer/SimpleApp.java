package com.stackverflow.nullpointer;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FilterFunction;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

import java.util.*;

public class SimpleApp {
    public static void main(String[] args) {
        // Should be some file on your system
        String logFile = "/Users/naman.nigam/GitHub/Naman/zealous-codetroops-libraries/mongo/src/main/resources/app.yml";
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();
        long numAs = logData.filter((FilterFunction<String>) s -> s.contains("a")).count();
        long numBs = logData.filter((FilterFunction<String>) s -> s.contains("b")).count();
        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);
        spark.stop();

        SparkConf conf = new SparkConf().setAppName("SparkEx1").setMaster("local[*]");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> df = sc.textFile("/home/hduser/sparkdata/usdata.csv");
        JavaRDD<String> fltr = df.flatMap(line -> Arrays.asList(line.split(" ")).iterator());
    }


}