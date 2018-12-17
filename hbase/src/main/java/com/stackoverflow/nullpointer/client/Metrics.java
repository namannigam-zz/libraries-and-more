package com.stackoverflow.nullpointer.client;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.Timer;

import java.util.concurrent.TimeUnit;

public enum Metrics {
    INSTANCE;

    private com.codahale.metrics.MetricRegistry metricRegistry;

    Metrics() {
        metricRegistry = new com.codahale.metrics.MetricRegistry();
        JmxReporter jmxReporter =
                JmxReporter.forRegistry(metricRegistry).inDomain("fk.metrics").convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
        jmxReporter.start();

        ConsoleReporter consoleReporter = ConsoleReporter.forRegistry(metricRegistry).convertRatesTo(TimeUnit.SECONDS).convertDurationsTo(TimeUnit.MILLISECONDS).build();
        consoleReporter.start(1000, TimeUnit.MILLISECONDS);
    }

    public com.codahale.metrics.MetricRegistry getRegistry() {
        return metricRegistry;
    }


    public static Timer.Context timerContext(Class<?> callerClass, String... names) {
        return INSTANCE.getRegistry().timer(com.codahale.metrics.MetricRegistry.name(callerClass, names)).time();
    }
}