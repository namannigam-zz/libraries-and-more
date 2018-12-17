package com.stackoverflow.nullpointer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.concurrent.CompletionException;

public class HttpTrial {

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 10_000; i++) {
            System.out.println("index: " + i);
            triggerHttp();
        }
    }

    private static void triggerHttp() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .setHeader("Connection","keep-alive")
                .uri(URI.create("https://stackoverflow.com/questions/53617574"))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        Map<String, String> responseMapped = new UncheckedObjectMapper().readValue(response.body());

        System.out.println(response.body());
        System.out.println(response.statusCode());

    }

    static class UncheckedObjectMapper extends ObjectMapper {
        Map<String, String> readValue(String content) {
            try {
                return this.readValue(content, new TypeReference<>() {
                });
            } catch (IOException ioe) {
                throw new CompletionException(ioe);
            }
        }
    }

}