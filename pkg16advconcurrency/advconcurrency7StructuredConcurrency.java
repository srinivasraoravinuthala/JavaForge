package pkg16advconcurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/*
 * advconcurrency7StructuredConcurrency.java
 * -----------------------------------------
 * The main method uses CompletableFuture. That code needs Java 8 and does not
 * use --enable-preview.
 *
 * Structured concurrency is still a preview in Java 25 (JEP 505). It is not
 * final. A program that calls it must be compiled and run with --enable-preview.
 * Java 25 opens a scope with StructuredTaskScope.open and a Joiner. It does not
 * use a public constructor:
 *
 *   try (var scope = StructuredTaskScope.open(
 *           StructuredTaskScope.Joiner.<String>anySuccessfulResultOrThrow())) {
 *       scope.fork(() -> fetchFrom("primary", 200));
 *       scope.fork(() -> fetchFrom("backup", 50));
 *       return scope.join();
 *   }
 *
 * Earlier previews used a public constructor. That shape is not the Java 25 API.
 */
public class advconcurrency7StructuredConcurrency {

    static String fetchFrom(String source, int delayMs) throws InterruptedException {
        Thread.sleep(delayMs);
        return "data-from-" + source;
    }

    public static void main(String[] args) throws Exception {
        // Failover race: first successful result wins (CompletableFuture stable equivalent)
        CompletableFuture<String> primary = CompletableFuture.supplyAsync(() -> {
            try { return fetchFrom("primary", 200); } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); throw new RuntimeException(e);
            }
        });
        CompletableFuture<String> backup = CompletableFuture.supplyAsync(() -> {
            try { return fetchFrom("backup", 50); } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); throw new RuntimeException(e);
            }
        });

        String winner = primary.applyToEither(backup, s -> s)
                .get(1, TimeUnit.SECONDS);
        System.out.println("Race winner: " + winner);

        // All must succeed in parallel
        CompletableFuture<String> db = CompletableFuture.supplyAsync(() -> {
            try { return fetchFrom("db", 30); } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); throw new RuntimeException(e);
            }
        });
        CompletableFuture<String> cache = CompletableFuture.supplyAsync(() -> {
            try { return fetchFrom("cache", 20); } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); throw new RuntimeException(e);
            }
        });
        System.out.println("Parallel: " + db.get() + " + " + cache.get());
    }
}
