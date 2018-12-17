package com.stackoverflow.nullpointer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.util.stream.Collectors.toList;

public class ConcurrentRequests {

    public void getURIs(List<URI> uris) {
        HttpClient client = HttpClient.newHttpClient();
        List<HttpRequest> requests = uris.stream()
                .map(HttpRequest::newBuilder)
                .map(HttpRequest.Builder::build)
                .collect(toList());

        CompletableFuture.allOf(requests.stream()
                .map(request -> client.sendAsync(request, ofString()))
                .toArray(CompletableFuture<?>[]::new))
                .join();
    }
//
//    public CompletableFuture<Map<String, String>> JSONBodyAsMap(URI uri) {
//        UncheckedObjectMapper objectMapper = new UncheckedObjectMapper();
//
//        HttpRequest request = HttpRequest.newBuilder(uri)
//                .header("Accept", "application/json")
//                .build();
//
//        return HttpClient.newHttpClient()
//                .sendAsync(request, ofString())
//                .thenApply(HttpResponse::body)
//                .thenApply(objectMapper::readValue);
//    }

//    class UncheckedObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
//        /**
//         * Parses the given JSON string into a Map.
//         */
//        Map<String, String> readValue(String content) {
//            try {
//                return this.readValue(content, new TypeReference<>() {
//                });
//            } catch (IOException ioe) {
//                throw new CompletionException(ioe);
//            }
//        }
//    }
//
//    public CompletableFuture<Void> postJSON(URI uri, Map<String, String> map) {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper
//                .writerWithDefaultPrettyPrinter()
//                .writeValueAsString(map);
//
//        HttpRequest request = HttpRequest.newBuilder(uri)
//                .header("Content-Type", "application/json")
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//
//        return HttpClient.newHttpClient()
//                .sendAsync(request, ofString())
//                .thenApply(HttpResponse::statusCode)
//                .thenAccept(System.out::println);
//    }

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

}