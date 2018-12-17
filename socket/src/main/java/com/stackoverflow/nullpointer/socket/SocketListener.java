package com.stackoverflow.nullpointer.socket;

import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class SocketListener implements WebSocket.Listener {

    public void onOpen(WebSocket webSocket) {
        webSocket.request(1);
        System.out.println("WebSocket listener has been opened for requests.");
    }

    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println("WebSocket listener has been closed with statusCode - " + statusCode);
        webSocket.sendClose(statusCode, reason);
        return new CompletableFuture<Void>();
    }

    public void onError(WebSocket webSocket, Throwable error) {
        System.out.println(error.getCause() + " exception was thrown. Message -  " + error.getLocalizedMessage());
        webSocket.abort();
    }

    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Ping: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return CompletableFuture.completedFuture("Ping completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        webSocket.request(1);
        System.out.println("Pong: Client ---> Server");
        System.out.println(message.asCharBuffer().toString());
        return CompletableFuture.completedFuture("Pong completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        System.out.println(data);
        webSocket.request(1);
        return CompletableFuture.completedFuture("onText() completed.").thenAccept(System.out::println);
    }

    public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
        System.out.println(data.toString());
        webSocket.request(1);
        return CompletableFuture.completedFuture("onBinary() completed.").thenAccept(System.out::println);
    }
}