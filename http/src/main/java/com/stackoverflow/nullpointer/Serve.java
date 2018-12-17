package com.stackoverflow.nullpointer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Serve {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path testPath = Paths.get(System.getProperty("user.dir"), "jsonSample.txt");
        byte[] testb = Files.readAllBytes(testPath);
//        System.out.println(new String(testb));

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.stackoverflow.com/"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
//                  .header( "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36" )
                .POST(HttpRequest.BodyPublishers.ofFile(testPath))
                .build();

        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(response.body());
    }
}