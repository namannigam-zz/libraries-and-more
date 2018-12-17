package com;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaCache {

    public static void main(String[] args) {
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().initialCapacity(4).build(new CacheLoader<>() {
            @Override
            public String load(String s) {
                return "nullpointer";
            }
        });
    }
}
