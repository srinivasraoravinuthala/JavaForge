package pkg16advconcurrency;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * advconcurrency1CountDownLatch.java
 * ----------------------------------
 * CountDownLatch: one or more threads wait until a counter reaches zero.
 *
 * DEFINITION:
 *   A latch is a one-shot synchronizer. Threads call await() to block; other
 *   threads call countDown() to decrement. When count hits 0, all waiters proceed.
 *
 * KEY POINTS:
 *   - Cannot reset — use CyclicBarrier if you need reuse.
 *   - Use case: start N workers, wait for all to finish (or ready signal).
 *   - await(timeout) avoids indefinite blocking.
 */
public class advconcurrency1CountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        int workers = 4;
        CountDownLatch startGate = new CountDownLatch(1);   // boss releases workers
        CountDownLatch doneGate  = new CountDownLatch(workers); // workers signal done

        try (ExecutorService pool = Executors.newFixedThreadPool(workers)) {
            for (int i = 0; i < workers; i++) {
                int id = i;
                pool.submit(() -> {
                    try {
                        startGate.await();                    // wait for boss
                        System.out.println("  worker " + id + " working...");
                        Thread.sleep(50 + id * 20);
                        doneGate.countDown();                   // signal completion
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                });
            }
            Thread.sleep(100);                                // workers are waiting
            System.out.println("Boss: releasing workers");
            startGate.countDown();
            doneGate.await(5, TimeUnit.SECONDS);              // wait for all workers
            System.out.println("Boss: all workers finished");
        }
    }
}
