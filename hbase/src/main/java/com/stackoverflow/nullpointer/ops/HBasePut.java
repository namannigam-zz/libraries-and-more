package com.stackoverflow.nullpointer.ops;

import com.codahale.metrics.Timer;
import com.stackoverflow.nullpointer.client.HBaseClient;
import com.stackoverflow.nullpointer.client.Metrics;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.MurmurHash3;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class HBasePut {

    private static Logger logger = Logger.getLogger(HBasePut.class.getName());
    private static Connection connection;

    private static Configuration getConfiguration(String ipcPoolSize) {
        Configuration config = HBaseConfiguration.create();
        config.set(HConstants.ZOOKEEPER_QUORUM, "10.32.58.133");
        config.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2181");
        config.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase-unsecure");
        config.set(HConstants.HBASE_CLIENT_IPC_POOL_SIZE, ipcPoolSize);
        return config;
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        if (args.length != 6) {
            System.out.println("<userCount>, <threadCount>, <ipcPoolSize> , <attemptCount> , <questionCount>, " +
                    "<startUserRange>");
            System.exit(1);
        }
        int userCount = Integer.parseInt(args[0]);
        int threadCount = Integer.parseInt(args[1]);
        String ipcPoolSize = args[2];
        int attemptCount = Integer.parseInt(args[3]);
        int questionCount = Integer.parseInt(args[4]);
        int startUser = Integer.parseInt(args[5]);

        logger.info("Trying to connect...");
        connection = ConnectionFactory.createConnection(getConfiguration(ipcPoolSize));
        logger.info("Connection created successfully!");

        int work = (userCount / threadCount);
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<WorkPerThread> workPerThreads = IntStream.range(0, threadCount)
                .mapToObj(i -> new WorkPerThread(i, work, attemptCount, questionCount, startUser))
                .collect(Collectors.toCollection(() -> new ArrayList<>(threadCount)));

//        List<PutToCall> putToCallList = new ArrayList<>(threadCount);
//        for(int i = 0; i < threadCount; i++) {
//            final int start = (i * work) + 1;
//            final int end = (i * work) + work;
//            questionCount = 1;
//            while (true) {
//                IntStream.range(start, end).forEach(a -> {
//                    String rowKey = String.valueOf(
//                            Math.abs(MurmurHash3.getInstance().hash(String.format("ACC%07d", a).getBytes())));
//                    putToCallList.add(new PutToCall(rowKey, "a", String.format("Q%02d", questionCount),
//                            String.valueOf(new Random().nextInt(100))));
//                });
//                questionCount++;
//                if (questionCount == 10) questionCount = 1;
//            }
//        }

        AtomicInteger atomicInteger = new AtomicInteger();
        long startTime = System.currentTimeMillis();
        List<Future<Void>> futures = executorService.invokeAll(workPerThreads);
        futures.forEach(future -> {
            try {
                future.get();
                atomicInteger.getAndIncrement();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        System.out.println("All done " + atomicInteger.get() + "  in " + (System.currentTimeMillis() - startTime));
        executorService.shutdown();
    }

    public static class WorkPerThread implements Callable<Void> {
        private int threadNumber;
        private int work;
        private int attemptCount;
        private int questionCount;
        private int startUser;

        WorkPerThread(int threadNumber, int work, int attemptCount, int questionCount, int startUser) {
            this.threadNumber = threadNumber;
            this.work = work;
            this.attemptCount = attemptCount;
            this.questionCount = questionCount;
            this.startUser = startUser;
        }


        @Override
        public Void call() {
            final int start = startUser + (threadNumber * work) + 1;
            final int end = startUser + (threadNumber * work) + work;
            for (int att = 0; att < attemptCount; att++) {
                for (int q = 0; q < questionCount; q++) {
                    for (int a = start; a <= end; a++) {
//                        String rowKey = String.valueOf(
//                                Math.abs(MurmurHash3.getInstance().hash(String.format("ACC%07d", a).getBytes())));

                        String rowKey = String.format("ACC%07d", a);
                        try {
                            putData(rowKey, "a", String.format("Q%02d", q), String.valueOf(new Random().nextInt(100)));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return null;
        }
    }

    private static void putData(String rowKey, String columnFamily, String columnQualifier, String columnValue)
            throws IOException {
        Timer.Context context = Metrics.timerContext(HBasePut.class, "put");
        Put put = new Put(rowKey.getBytes());
        put.addColumn(columnFamily.getBytes(), columnQualifier.getBytes(), Bytes.toBytes(columnValue));
        Table table = connection.getTable(HBaseClient.getTableName());
        table.put(put);
        table.close();
        context.stop();
    }


    public static class PutToCall implements Callable<Void> {
        private String rowKey;
        private String columnFamily;
        private String columnQualifier;
        private String columnValue;

        PutToCall(String rowKey, String columnFamily, String columnQualifier, String columnValue) {
            this.rowKey = rowKey;
            this.columnFamily = columnFamily;
            this.columnQualifier = columnQualifier;
            this.columnValue = columnValue;
        }

        @Override
        public Void call() throws Exception {
            putData(this.rowKey, this.columnFamily, this.columnQualifier, this.columnValue);
            return null;
        }
    }

    private static void putData(int numberOfUsers) throws IOException {
        Random random = new Random();
        List<Put> putsPerRow = new ArrayList<>();
        for (int i = 0; i < numberOfUsers; i++) {
            String rowKey = "ACC" + String.format("%07d", i);
            IntStream.rangeClosed(1, 10).mapToObj(j -> "Q" + String.format("%02d", j)).forEach(qualifierName -> {
                int value = random.nextInt(100);
                Put put = new Put(rowKey.getBytes());
                put.addColumn("a".getBytes(), qualifierName.getBytes(), Bytes.toBytes(value));
                putsPerRow.add(put);
            });
        }
        connection.getTable(HBaseClient.getTableName()).put(putsPerRow);
        logger.info("Put completed!");
    }

    private static void clearTable(String rowKey, String columnFamily, String columnQualifier, String columnValue)
            throws IOException {
        Put put = new Put(rowKey.getBytes());
        put.addColumn(columnFamily.getBytes(), columnQualifier.getBytes(), Bytes.toBytes(columnValue));
        connection.getAdmin().deleteTable(HBaseClient.getTableName());
    }
}