package pkg16advconcurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * advconcurrency3Semaphore.java
 * -----------------------------
 * Semaphore: limit concurrent access to a resource (permits).
 *
 * DEFINITION:
 *   A semaphore maintains a set of permits. acquire() takes one (blocks if none);
 *   release() returns one. Fair semaphores queue waiting threads in order.
 *
 * KEY POINTS:
 *   - Binary semaphore (1 permit) acts like a lock but can release from another thread.
 *   - Use for connection pools, rate limiting, parking slots.
 *   - Always release in finally — leaked permits starve other threads.
 */
public class advconcurrency3Semaphore {

    public static void main(String[] args) throws InterruptedException {
        int maxConcurrent = 2;
        Semaphore pool = new Semaphore(maxConcurrent, true); // fair

        try (ExecutorService exec = Executors.newFixedThreadPool(5)) {
            for (int i = 0; i < 5; i++) {
                int id = i;
                exec.submit(() -> {
                    try {
                        System.out.println("  task " + id + " waiting for permit");
                        pool.acquire();
                        System.out.println("  task " + id + " acquired permit, running");
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        pool.release();
                        System.out.println("  task " + id + " released permit");
                    }
                });
            }
            exec.shutdown();
            exec.awaitTermination(5, TimeUnit.SECONDS);
        }
        System.out.println("Max " + maxConcurrent + " tasks ran concurrently at any time");
    }
}
