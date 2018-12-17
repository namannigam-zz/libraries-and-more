package com.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PreloadCacheTest {

    @Test
    public void whenPreloadCache_thenUsePutAll() {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<>() {
            @Override
            public String load(String key) {
                return key.toUpperCase();
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder().build(loader);

        Map<String, String> map = new HashMap<>();
        map.put("first", "FIRST");
        map.put("second", "SECOND");
        cache.putAll(map);

        assertEquals(2, cache.size());
    }
}
