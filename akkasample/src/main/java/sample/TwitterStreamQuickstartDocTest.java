package sample;

import akka.Done;
import akka.NotUsed;
import akka.actor.ActorSystem;
import akka.stream.ActorMaterializer;
import akka.stream.Materializer;
import akka.stream.javadsl.Source;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class TwitterStreamQuickstartDocTest {

    public static class Author {
        public final String handle;

        public Author(String handle) {
            this.handle = handle;
        }

    }

    public static class Hashtag {
        public final String name;

        public Hashtag(String name) {
            this.name = name;
        }
    }

    public static class Tweet {
        public final Author author;
        public final long timestamp;
        public final String body;

        public Tweet(Author author, long timestamp, String body) {
            this.author = author;
            this.timestamp = timestamp;
            this.body = body;
        }

        public Set<Hashtag> hashtags() {
            return Arrays.stream(body.split(" "))
                    .filter(a -> a.startsWith("#"))
                    .map(Hashtag::new)
                    .collect(Collectors.toSet());
        }
    }

    public static final Hashtag AKKA = new Hashtag("#akka");

    public static void main(String[] args) throws TimeoutException, InterruptedException, ExecutionException {

        final ActorSystem system = ActorSystem.create("reactive-tweets");
        final Materializer mat = ActorMaterializer.create(system);

        Source<Tweet, NotUsed> tweets = Source.from(
                Arrays.asList(
                        new Tweet(new Author("rolandkuhn"), System.currentTimeMillis(), "#akka rocks!"),
                        new Tweet(new Author("patriknw"), System.currentTimeMillis(), "#akka !"),
                        new Tweet(new Author("bantonsson"), System.currentTimeMillis(), "#akka !"),
                        new Tweet(new Author("drewhk"), System.currentTimeMillis(), "#akka !"),
                        new Tweet(new Author("ktosopl"), System.currentTimeMillis(), "#akka on rocks!"),
                        new Tweet(new Author("mmartynas"), System.currentTimeMillis(), "wow #akka !"),
                        new Tweet(new Author("akkateam"), System.currentTimeMillis(), "#akka rocks!"),
                        new Tweet(new Author("bananaman"), System.currentTimeMillis(), "#bananas rock!"),
                        new Tweet(new Author("appleman"), System.currentTimeMillis(), "#apples rock!"),
                        new Tweet(new Author("drama"), System.currentTimeMillis(), "#apples to #oranges!")
                ));

        final Source<Author, NotUsed> authors = tweets.filter(t -> t.hashtags().contains(AKKA)).map(t -> t.author);
        CompletionStage<Done> done = authors.runForeach(t -> System.out.println(t.handle), mat);
        done.thenRun(system::terminate);


//        final Source<Hashtag, NotUsed> hashtags = tweets.mapConcat(t -> new ArrayList<>(t.hashtags()));
//        done = hashtags.runForeach(t-> System.out.println(t.name), mat);
//        done.thenRun(system::terminate);

    }

}