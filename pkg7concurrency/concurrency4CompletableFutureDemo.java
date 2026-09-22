package pkg7concurrency;

/*
 * concurrency4CompletableFutureDemo.java
 * --------------------------
 * Asynchronous pipelines: chain, combine, and handle errors without blocking.
 *
 * KEY METHODS:
 *   supplyAsync / runAsync   : start async work.
 *   thenApply / thenCompose  : transform / flat-map the result.
 *   thenCombine              : combine two independent futures.
 *   exceptionally / handle   : recover from failures.
 *   allOf / anyOf            : coordinate multiple futures.
 */
import java.util.concurrent.*;

public class concurrency4CompletableFutureDemo {

    static int slow(int x) {
        try { Thread.sleep(30); } catch (InterruptedException ignored) {}
        return x;
    }

    public static void main(String[] args) throws Exception {
        // Chain transformations
        CompletableFuture<String> pipeline = CompletableFuture
                .supplyAsync(() -> slow(10))
                .thenApply(x -> x * 2)               // 20
                .thenApply(x -> "result=" + x);
        System.out.println(pipeline.get());

        // thenCompose: dependent async step (flat-map)
        CompletableFuture<Integer> composed = CompletableFuture
                .supplyAsync(() -> slow(5))
                .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + 100));
        System.out.println("composed: " + composed.get());

        // thenCombine: merge two independent computations
        CompletableFuture<Integer> a = CompletableFuture.supplyAsync(() -> slow(3));
        CompletableFuture<Integer> b = CompletableFuture.supplyAsync(() -> slow(4));
        System.out.println("combined a+b: " + a.thenCombine(b, Integer::sum).get());

        // Error handling
        CompletableFuture<Integer> recovered = CompletableFuture
                .<Integer>supplyAsync(() -> { throw new RuntimeException("boom"); })
                .exceptionally(ex -> { System.out.println("recovered from: " + ex.getMessage()); return -1; });
        System.out.println("recovered value: " + recovered.get());

        // allOf: wait for many
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> slow(1));
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> slow(2));
        CompletableFuture.allOf(f1, f2).join();
        System.out.println("allOf done: " + f1.get() + ", " + f2.get());
    }
}
