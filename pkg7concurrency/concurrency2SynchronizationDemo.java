package pkg7concurrency;

/*
 * concurrency2SynchronizationDemo.java
 * ------------------------
 * Demonstrates a RACE CONDITION and three fixes: synchronized, AtomicInteger,
 * and a lock. Also shows why `volatile` alone is NOT enough for count++.
 *
 * RACE CONDITION: count++ is read-modify-write (3 steps); concurrent threads
 * interleave and lose updates.
 */
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.*;

public class concurrency2SynchronizationDemo {

    static int unsafe = 0;                          // racy
    static int guarded = 0;                          // protected by `lock`
    static final AtomicInteger atomic = new AtomicInteger();
    static final Object monitor = new Object();
    static final ReentrantLock lock = new ReentrantLock();
    static int synced = 0;

    static void incUnsafe()  { unsafe++; }
    static void incSynced()  { synchronized (monitor) { synced++; } }
    static void incLocked()  { lock.lock(); try { guarded++; } finally { lock.unlock(); } }

    public static void main(String[] args) throws InterruptedException {
        final int THREADS = 8, PER = 50_000, EXPECTED = THREADS * PER;

        run("unsafe (race)", THREADS, PER, concurrency2SynchronizationDemo::incUnsafe);
        run("synchronized",  THREADS, PER, concurrency2SynchronizationDemo::incSynced);
        run("ReentrantLock", THREADS, PER, concurrency2SynchronizationDemo::incLocked);
        run("AtomicInteger", THREADS, PER, atomic::incrementAndGet);

        System.out.println("\nEXPECTED        = " + EXPECTED);
        System.out.println("unsafe          = " + unsafe + "   <- usually LESS (lost updates)");
        System.out.println("synchronized    = " + synced);
        System.out.println("ReentrantLock   = " + guarded);
        System.out.println("AtomicInteger   = " + atomic.get());
    }

    static void run(String label, int threads, int per, Runnable inc) throws InterruptedException {
        Thread[] ts = new Thread[threads];
        for (int i = 0; i < threads; i++) {
            ts[i] = new Thread(() -> { for (int j = 0; j < per; j++) inc.run(); });
            ts[i].start();
        }
        for (Thread t : ts) t.join();
    }
}
