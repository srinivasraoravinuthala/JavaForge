package pkg16advconcurrency;

import java.util.concurrent.Phaser;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * advconcurrency4Phaser.java
 * --------------------------
 * Phaser: flexible, reusable barrier with dynamic party count.
 *
 * DEFINITION:
 *   Phaser is like CyclicBarrier but parties can register/deregister dynamically
 *   and supports tiered phases (phase number increments each trip).
 *
 * KEY POINTS:
 *   - arriveAndAwaitAdvance() waits for current phase to complete.
 *   - register() / arriveAndDeregister() change party count at runtime.
 *   - Useful for fork/join style pipelines with varying parallelism.
 */
public class advconcurrency4Phaser {

    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(1); // main is party 0

        try (ExecutorService pool = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 3; i++) {
                int id = i;
                phaser.register();
                pool.submit(() -> {
                    System.out.println("  worker " + id + " phase 0");
                    phaser.arriveAndAwaitAdvance();
                    System.out.println("  worker " + id + " phase 1");
                    phaser.arriveAndDeregister();
                });
            }
            phaser.arriveAndAwaitAdvance(); // wait phase 0
            System.out.println("Main: phase 0 complete");
            phaser.arriveAndAwaitAdvance(); // wait phase 1
            System.out.println("Main: phase 1 complete");
            pool.shutdown();
            pool.awaitTermination(3, TimeUnit.SECONDS);
        }
    }
}
