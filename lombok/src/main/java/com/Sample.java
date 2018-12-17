package com;

import lombok.Getter;
import lombok.Value;
import reactor.core.publisher.Mono;
import reactor.ipc.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Sample {

    @Getter(lazy = true)
    private final Mono<Map<String, Translations.Translation>> translations = HttpClient.create()
            .get(String.format("%s/translations/%s", "ep", "tg"), Function.identity())
            .flatMap(it -> it.receive().aggregate().asByteArray())
            .map(byteArray -> new Translations(new HashMap<>()))
            .map(Translations::getTranslations)
            .retryWhen(it -> it.delayElements(Duration.ofMillis(200)))
            .cache(Duration.ofMinutes(5))
            .timeout(Duration.ofSeconds(10));


    @Getter(lazy = true)
    private final Mono<Map<String, Translations.Translation>> translationz = Mono.just(new byte[]{})
            .map(byteArray -> new Translations(new HashMap<>()))
            .map(Translations::getTranslations)
            .retryWhen(it -> it.delayElements(Duration.ofMillis(200)))
            .cache(Duration.ofMinutes(5))
            .timeout(Duration.ofSeconds(10));

    @Value
    public static class Translations {

        Map<String, Translation> translations;

        @Value
        public static class Translation {
            public Translation(Map<String, String> content) {
                this.content = content;
            }

            Map<String, String> content;

        }
    }

    public static void main(String[] args) {

    }
}