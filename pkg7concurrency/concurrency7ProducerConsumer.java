package pkg7concurrency;

/*
 * concurrency7ProducerConsumer.java
 * ---------------------
 * The classic producer/consumer problem solved with a BlockingQueue, which
 * handles all the waiting/signaling for you (no manual wait/notify).
 *
 * BlockingQueue.put() blocks when full; take() blocks when empty.
 * A "poison pill" signals consumers to stop.
 */
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class concurrency7ProducerConsumer {

    private static final int POISON = -1;

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);  // bounded buffer
        AtomicInteger consumedSum = new AtomicInteger();
        int items = 20, consumers = 3;

        // Producer
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= items; i++) queue.put(i);   // blocks if full
                for (int c = 0; c < consumers; c++) queue.put(POISON);  // stop signals
            } catch (InterruptedException ignored) {}
        }, "producer");

        // Consumers
        Thread[] cons = new Thread[consumers];
        for (int c = 0; c < consumers; c++) {
            cons[c] = new Thread(() -> {
                try {
                    while (true) {
                        int v = queue.take();                    // blocks if empty
                        if (v == POISON) return;
                        consumedSum.addAndGet(v);
                    }
                } catch (InterruptedException ignored) {}
            }, "consumer-" + c);
        }

        producer.start();
        for (Thread t : cons) t.start();
        producer.join();
        for (Thread t : cons) t.join();

        int expected = items * (items + 1) / 2;   // 1..20 sum = 210
        System.out.println("consumed sum = " + consumedSum.get() + " (expected " + expected + ")");
        System.out.println("match: " + (consumedSum.get() == expected));
    }
}
