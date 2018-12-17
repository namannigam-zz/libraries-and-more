package com.stackoverflow.nullpointer.sample;

import com.stackoverflow.nullpointer.socket.SocketListener;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.WebSocket;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WebServer {
    private final WebSocket server;
    private static final String ENDPOINT = "ws://localhost:8080/"; // tried "ws://127.0.0.1:8080/" this as well

    WebServer() throws InterruptedException, ExecutionException {
        WebSocket.Listener listener = new SocketListener();
        WebSocket.Builder builder = HttpClient.newHttpClient().newWebSocketBuilder();
        CompletableFuture<WebSocket> webSocketCompletableFuture = builder.buildAsync(URI.create(ENDPOINT), listener);
        server = webSocketCompletableFuture.get();
    }

    WebSocket getWebSocket() {
        return this.server;
    }
}