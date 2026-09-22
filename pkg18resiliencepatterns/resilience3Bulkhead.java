package pkg18resiliencepatterns;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/*
 * resilience3Bulkhead.java
 * ------------------------
 * Bulkhead: isolate resources so one slow/failing area cannot exhaust the pool.
 *
 * DEFINITION:
 *   Like ship bulkheads that contain flooding. Limit concurrent calls to a
 *   dependency (semaphore/thread pool) so other parts of the system stay healthy.
 *
 * KEY POINTS:
 *   - Separate pools for DB, HTTP, cache — don't share one giant pool.
 *   - Reject or queue when bulkhead is full (fail fast vs wait).
 *   - Pair with circuit breaker for defense in depth.
 */
public class resilience3Bulkhead {

    static class Bulkhead {
        private final Semaphore permits;

        Bulkhead(int maxConcurrent) { permits = new Semaphore(maxConcurrent); }

        void run(Runnable task) throws InterruptedException {
            if (!permits.tryAcquire(100, TimeUnit.MILLISECONDS)) {
                throw new RuntimeException("Bulkhead full — rejected");
            }
            try {
                task.run();
            } finally {
                permits.release();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bulkhead dbBulkhead = new Bulkhead(2); // only 2 concurrent DB calls
        ExecutorService exec = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            int id = i;
            exec.submit(() -> {
                try {
                    dbBulkhead.run(() -> {
                        System.out.println("  task " + id + " in bulkhead");
                        try { Thread.sleep(300); } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    });
                } catch (Exception e) {
                    System.out.println("  task " + id + " rejected: " + e.getMessage());
                }
            });
        }
        exec.shutdown();
        exec.awaitTermination(5, TimeUnit.SECONDS);
    }
}
