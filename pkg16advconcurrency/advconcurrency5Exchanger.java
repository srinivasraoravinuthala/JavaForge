package pkg16advconcurrency;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/*
 * advconcurrency5Exchanger.java
 * -----------------------------
 * Exchanger: two threads swap objects at a synchronization point.
 *
 * DEFINITION:
 *   exchange(V) blocks until another thread also calls exchange, then both receive
 *   the other's value. Only two parties per exchanger instance.
 *
 * KEY POINTS:
 *   - Classic use: producer fills buffer, consumer takes buffer, swap empty/full.
 *   - exchange(timeout) avoids indefinite wait.
 *   - For N-way exchange use other structures (queues).
 */
public class advconcurrency5Exchanger {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();

        try (ExecutorService pool = Executors.newFixedThreadPool(2)) {
            pool.submit(() -> {
                try {
                    String received = exchanger.exchange("from-A");
                    System.out.println("Thread A received: " + received);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            pool.submit(() -> {
                try {
                    Thread.sleep(100);
                    String received = exchanger.exchange("from-B");
                    System.out.println("Thread B received: " + received);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            pool.shutdown();
            pool.awaitTermination(3, TimeUnit.SECONDS);
        }
    }
}
