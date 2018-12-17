package com.stackoverflow.nullpointer.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public interface Transport {

    <T> CompletableFuture<T> sendText(CharSequence message,
                                      boolean isLast,
                                      T attachment,
                                      BiConsumer<? super T, ? super Throwable> action);

    <T> CompletableFuture<T> sendBinary(ByteBuffer message,
                                        boolean isLast,
                                        T attachment,
                                        BiConsumer<? super T, ? super Throwable> action);

    <T> CompletableFuture<T> sendPing(ByteBuffer message,
                                      T attachment,
                                      BiConsumer<? super T, ? super Throwable> action);

    <T> CompletableFuture<T> sendPong(ByteBuffer message,
                                      T attachment,
                                      BiConsumer<? super T, ? super Throwable> action);

    /*
     * Sends a Pong message with initially unknown data. Used for sending the
     * most recent automatic Pong reply.
     */
    <T> CompletableFuture<T> sendPong(Supplier<? extends ByteBuffer> message,
                                      T attachment,
                                      BiConsumer<? super T, ? super Throwable> action);

    <T> CompletableFuture<T> sendClose(int statusCode,
                                       String reason,
                                       T attachment,
                                       BiConsumer<? super T, ? super Throwable> action);

    void request(long n);

    /*
     * Why is this method needed? Since receiving of messages operates through
     * callbacks this method allows to abstract out what constitutes as a
     * message being received (i.e. to decide outside this type when exactly one
     * should decrement the demand).
     */
    void acknowledgeReception();

    /*
     * If this method is invoked, then all pending and subsequent send
     * operations will fail with IOException.
     */
    void closeOutput() throws IOException;

    void closeInput() throws IOException;
}