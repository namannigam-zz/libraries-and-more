package com.stackoverflow.nullpointer.ops;

import com.stackoverflow.nullpointer.client.HBaseClient;
import org.apache.commons.math3.util.Pair;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.stackoverflow.nullpointer.client.HBaseClient.getTableName;


public class HBaseScan {

    private static Logger logger = Logger.getLogger(HBaseScan.class.getName());
    private static int batchSize;
    private static int cacheSize;
    private static final List<Pair<String, String>> scans = new ArrayList<>();
    private static Connection connection;


    public static void main(String[] args) throws InterruptedException {
        connection = HBaseClient.getConnection();
        batchSize = Integer.parseInt(args[0]);
        cacheSize = Integer.parseInt(args[1]);
        int threads = Integer.parseInt(args[2]);
        int loopCounter = Integer.parseInt(args[3]);
        int interval = 999999 / threads;
        int start = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(threads);
        for(int k = 0; k < loopCounter; k++) {
            for(int i = 0; i < threads; i++) {
                String startRow = ("000000" + (start));
                startRow = startRow.substring(startRow.length() - 6);
                String endRow = "000000" + (start + interval);
                endRow = endRow.substring(endRow.length() - 6);
                if (i == threads - 1) {
                    endRow = "999999";
                }
                start += interval;
                scans.add(new Pair<>(startRow, endRow));
            }
            AtomicInteger atomicInteger = new AtomicInteger();
            long startTime = System.currentTimeMillis();
            List<Scanner> scanners = scans.stream().map(scan -> new Scanner(scan.getKey(), scan.getValue())).collect(Collectors.toList());
            List<Future<Integer>> futures = executorService.invokeAll(scanners);
            futures.forEach(future -> {
                try {
                    atomicInteger.addAndGet(future.get());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("All done " + atomicInteger.get() + "  in " + (System.currentTimeMillis() - startTime));
        }
        executorService.shutdown();
    }

    public static class Scanner implements Callable<Integer> {
        private String start;
        private String end;

        Scanner(String start, String end) {
            this.start = start;
            this.end = end;
        }

        @Override public Integer call() throws Exception {
            return scanData(this.start, this.end, batchSize, cacheSize);
        }
    }


    private static int scanData(String start, String end, int batch, int cache) throws IOException {
        Scan scan = new Scan();
//        scan.withStartRow(Bytes.toBytes("ACC0" + start)).withStopRow(Bytes.toBytes("ACC0" + end)); // 2.0.1 impl
        scan.setStartRow(Bytes.toBytes("ACC0" + start)).setStopRow(Bytes.toBytes("ACC0" + end));
        scan.setBatch(batch).setCaching(cache);
//        scan.setCacheBlocks(false);

        final AtomicInteger counter = new AtomicInteger(0);
        try (HTable hTable = (HTable) connection.getTable(getTableName())) {
            ResultScanner resultScanner = hTable.getScanner(scan);
            resultScanner.iterator().forEachRemaining(rs -> counter.addAndGet(1));
        }
        return counter.get();
    }


    private static void scanData(int batch, int cache) throws IOException {
        logger.info(String.format("Scan Start Time -- %s | Batch -- %s | Cache -- %s", System.currentTimeMillis(), batch,
                cache));

        Scan scan = new Scan().setStartRow(Bytes.toBytes(0x0)).setStopRow(Bytes.toBytes(0x7fffffff)).setBatch(batch)
                .setCaching(cache);
        try (HTable hTable = (HTable) connection.getTable(getTableName())) {
            ResultScanner resultScanner = hTable.getScanner(scan);
            resultScanner.iterator().forEachRemaining(rs -> {
            });
        }
        logger.info(String.format("Scan End Time -- %s", System.currentTimeMillis()));
    }
}