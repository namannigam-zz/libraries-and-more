package com.stackoverflow.nullpointer.util;

public final class Pair<T, U> {

    private Pair(T first, U second) {
        this.second = second;
        this.first = first;
    }

    private final T first;
    private final U second;

    static <T, U> Pair<T, U> pair(T first, U second) {
        return new Pair<>(first, second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}