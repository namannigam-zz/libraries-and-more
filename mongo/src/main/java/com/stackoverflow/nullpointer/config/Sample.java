package com.stackoverflow.nullpointer.config;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Sample {

    private static Predicate<String> isEmptyOrNull() {
        return s -> s == null || s.isEmpty();
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("", "one", "two", null, "three");
        strings.stream().filter(Predicate.not(isEmptyOrNull())).forEach(System.out::println);
    }
}