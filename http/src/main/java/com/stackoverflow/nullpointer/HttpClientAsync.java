package com.stackoverflow.nullpointer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;


import static java.net.http.HttpResponse.BodyHandlers.*;

public class HttpClientAsync {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://..."))
                .build();

        HttpResponse<InputStream> response = client
                .sendAsync(request, HttpResponse.BodyHandlers.ofInputStream())
                .get(); // this finishes as soon as the header is received

        try {
            InputStream stream = response.body();
            byte[] test = stream.readNBytes(20); // trying to read just a few bytes
            // but it waits for the whole body
        } catch (IOException ex) {
        }
    }

    public CompletableFuture<String> get(String uri) {
        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        return client.sendAsync(request, ofString())
                .thenApply(HttpResponse::body);
    }

    public void getByteArray(String uri) {
        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress("www-proxy.com", 8080)))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        client.sendAsync(request, ofByteArrayConsumer(bytes -> System.out.println(bytes + "received")));
    }
}