package com.stackoverflow.nullpointer.sample;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class SocketsDemo {

    public static void main(String[] args) {
        try {
            var server = new WebServer().getWebSocket();
            server.sendPing(ByteBuffer.wrap("Ping: Client <--- Server".getBytes(Charset.forName("UTF-16"))));
            server.sendPong(ByteBuffer.wrap("Pong: Client <--- Server".getBytes(Charset.forName("UTF-16"))));
            server.sendText("Hello World!", false);
            server.sendClose(1001, "Goodbye!");
        } catch (Exception e) {
            System.out.println("Failure:" + e.getClass().toString() + " was thrown. Message: " + e.getMessage());
            if (e.getMessage().contains("WebSocketHandshakeException")) {
                var ex = ((java.net.http.WebSocketHandshakeException) e.getCause()).getResponse();
                System.out.println("Body:\t" + ex.body());
                System.out.println("Headers:");
                ex.headers().map().forEach((k, v) -> System.out.println("\t" + k + ":  " + v));
                System.out.println("HTTP request:  " + ex.request());
                System.out.println("HTTP version:  " + ex.version());
                System.out.println("Previous Reponse?:  " + ex.previousResponse());
            }
        }
    }
}