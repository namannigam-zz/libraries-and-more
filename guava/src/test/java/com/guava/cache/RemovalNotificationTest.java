package com.guava.cache;

import com.google.common.cache.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemovalNotificationTest {

    @Test
    void whenEntryRemovedFromCache_thenNotify() {
        CacheLoader<String, String> loader;
        loader = new CacheLoader<>() {
            @Override
            public String load(final String key) {
                return key.toUpperCase();
            }
        };

        RemovalListener<String, String> listener;
        listener = n -> {
            if (n.wasEvicted()) {
                String cause = n.getCause().name();
                assertEquals(RemovalCause.SIZE.toString(), cause);
                System.out.println(cause);
            }
        };

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
                .maximumSize(3)
                .removalListener(listener)
                .build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last");
        assertEquals(3, cache.size());
    }
}