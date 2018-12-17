package com.stackoverflow.nullpointer;

import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HttClientHostHeader {


    public static void main(String[] args) throws InterruptedException, IOException {

        Executor executor = Executors.newFixedThreadPool(3);

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
                .build();

        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create("https://stackoverflow.com/questions/51907641"))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        HttpClient httpClient = HttpClient.newBuilder()
                .executor(executor)  // custom executor
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://stackoverflow.com/questions/51907641"))
                .build();
        System.out.println("main :: Thread is: " + Thread.currentThread().getName());

        httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    System.out.println(httpClient.executor().get().equals(executor));
                    System.out.println("asyn :: Thread is: " + Thread.currentThread().getName());
                })
                .getNow(ifAbsent());
    }

    private static Void ifAbsent() {
        System.out.println("ifAbsent :: Thread is: " + Thread.currentThread().getName());
        return null;
    }

    private static class Worker extends Thread {
        private int delay;
        Runnable runnable;

        Worker(int delay, String name, Runnable runnable) {
            super(name);
            this.delay = delay;
            this.runnable = runnable;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(delay);
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}