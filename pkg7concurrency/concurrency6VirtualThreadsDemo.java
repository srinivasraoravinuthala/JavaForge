package pkg7concurrency;

/*
 * concurrency6VirtualThreadsDemo.java  (Java 21, Project Loom)
 * ------------------------------------------------
 * Virtual threads are lightweight threads scheduled by the JVM onto a small pool
 * of carrier (platform) threads. They make blocking code scale to millions of
 * concurrent tasks â€” ideal for blocking I/O.
 *
 * PLATFORM vs VIRTUAL:
 *   - Platform thread: 1:1 with an OS thread, ~1MB stack, limited count.
 *   - Virtual thread : cheap (KBs), millions possible; blocks cheaply.
 *
 * WHEN NOT to use: CPU-bound work (no gain). In Java 21, a virtual thread can
 * stay pinned to its carrier inside synchronized. Java 24 (JEP 491) lets it
 * unmount there. This file shows the Java 21 API.
 */
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class concurrency6VirtualThreadsDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1) Start a single virtual thread
        Thread vt = Thread.ofVirtual().name("vt-demo").start(() ->
                System.out.println("hello from " + Thread.currentThread()));
        vt.join();
        System.out.println("isVirtual: " + vt.isVirtual());

        // 2) Launch 100,000 virtual threads, each doing a blocking sleep
        AtomicInteger completed = new AtomicInteger();
        long start = System.currentTimeMillis();
        try (ExecutorService exec = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < 100_000; i++) {
                exec.submit(() -> {
                    try { Thread.sleep(20); } catch (InterruptedException ignored) {}
                    completed.incrementAndGet();
                });
            }
        }   // close() waits for all tasks to finish
        long elapsed = System.currentTimeMillis() - start;

        System.out.println("completed " + completed.get() + " virtual-thread tasks in " + elapsed + "ms");
        System.out.println("(doing this with 100k platform threads would exhaust memory)");
    }
}
