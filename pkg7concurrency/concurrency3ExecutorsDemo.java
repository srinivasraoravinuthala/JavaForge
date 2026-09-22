package pkg7concurrency;

/*
 * concurrency3ExecutorsDemo.java
 * ------------------
 * Thread pools via ExecutorService: submit Callables, get Futures, invokeAll,
 * and shut down cleanly. Prefer pools over manually creating threads.
 *
 * POOL TYPES:
 *   - newFixedThreadPool(n)  : bounded worker pool.
 *   - newCachedThreadPool()  : grows/shrinks on demand.
 *   - newSingleThreadExecutor: serial execution.
 *   - newVirtualThreadPerTaskExecutor() : a virtual thread per task (Java 21).
 */
import java.util.*;
import java.util.concurrent.*;

public class concurrency3ExecutorsDemo {

    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(4);

        // submit a Callable -> get a Future
        Future<Integer> future = pool.submit(() -> {
            Thread.sleep(50);
            return 6 * 7;
        });
        System.out.println("future result: " + future.get());   // blocks until ready

        // invokeAll: run many tasks, collect results
        List<Callable<Integer>> tasks = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            int n = i;
            tasks.add(() -> n * n);
        }
        List<Future<Integer>> results = pool.invokeAll(tasks);
        List<Integer> squares = new ArrayList<>();
        for (Future<Integer> f : results) squares.add(f.get());
        System.out.println("squares 1..5: " + squares);

        // invokeAny: first successful result wins
        Integer any = pool.invokeAny(List.of(() -> 1, () -> 2, () -> 3));
        System.out.println("invokeAny returned one of {1,2,3}: " + any);

        // Always shut down the pool
        pool.shutdown();
        boolean done = pool.awaitTermination(2, TimeUnit.SECONDS);
        System.out.println("pool terminated cleanly: " + done);

        // Java 21: virtual-thread-per-task executor (great for blocking I/O)
        try (ExecutorService vexec = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> f = vexec.submit(() -> "ran on " + Thread.currentThread());
            System.out.println("virtual task: " + f.get());
        }   // try-with-resources closes (and awaits) the executor
    }
}
