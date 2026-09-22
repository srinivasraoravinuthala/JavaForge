package pkg16advconcurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * advconcurrency2CyclicBarrier.java
 * ---------------------------------
 * CyclicBarrier: N threads wait at a barrier, then all proceed together (reusable).
 *
 * DEFINITION:
 *   Unlike CountDownLatch, CyclicBarrier resets after all parties arrive. Optional
 *   barrier action runs once when the barrier trips (e.g. merge partial results).
 *
 * KEY POINTS:
 *   - await() blocks until all N parties reach the barrier.
 *   - Reusable across multiple phases (simulations, parallel merge steps).
 *   - BrokenBarrierException if a waiting thread is interrupted or times out.
 */
public class advconcurrency2CyclicBarrier {

    public static void main(String[] args) throws Exception {
        int parties = 3;
        CyclicBarrier barrier = new CyclicBarrier(parties, () ->
                System.out.println("  --- barrier action: all " + parties + " arrived ---"));

        try (ExecutorService pool = Executors.newFixedThreadPool(parties)) {
            for (int i = 0; i < parties; i++) {
                int id = i;
                pool.submit(() -> {
                    for (int phase = 1; phase <= 2; phase++) {
                        System.out.println("  thread " + id + " phase " + phase + " work");
                        try {
                            barrier.await();
                        } catch (BrokenBarrierException | InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                });
            }
            pool.shutdown();
            while (!pool.isTerminated()) Thread.sleep(50);
        }
        System.out.println("Done — barrier cycled twice across " + parties + " threads");
    }
}
