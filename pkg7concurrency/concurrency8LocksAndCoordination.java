package pkg7concurrency;

/*
 * concurrency8LocksAndCoordination.java
 * -------------------------
 * java.util.concurrent coordination tools: ReentrantLock, ReadWriteLock,
 * CountDownLatch, Semaphore, and a deadlock-avoidance note.
 */
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.atomic.AtomicInteger;

public class concurrency8LocksAndCoordination {

    public static void main(String[] args) throws InterruptedException {
        countDownLatchDemo();
        readWriteLockDemo();
        semaphoreDemo();
    }

    // CountDownLatch: wait for N tasks to complete before proceeding.
    static void countDownLatchDemo() throws InterruptedException {
        int workers = 4;
        CountDownLatch latch = new CountDownLatch(workers);
        for (int i = 0; i < workers; i++) {
            int id = i;
            new Thread(() -> {
                try { Thread.sleep(20 * id); } catch (InterruptedException ignored) {}
                latch.countDown();              // signal completion
            }).start();
        }
        latch.await();                          // block until count reaches 0
        System.out.println("[CountDownLatch] all " + workers + " workers finished");
    }

    // ReadWriteLock: many readers OR one writer (improves read-heavy throughput).
    static void readWriteLockDemo() throws InterruptedException {
        ReadWriteLock rw = new ReentrantReadWriteLock();
        AtomicInteger shared = new AtomicInteger(0);

        Runnable writer = () -> {
            rw.writeLock().lock();
            try { shared.incrementAndGet(); } finally { rw.writeLock().unlock(); }
        };
        Runnable reader = () -> {
            rw.readLock().lock();
            try { shared.get(); } finally { rw.readLock().unlock(); }
        };

        Thread[] ts = new Thread[6];
        for (int i = 0; i < ts.length; i++) ts[i] = new Thread(i % 2 == 0 ? reader : writer);
        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();
        System.out.println("[ReadWriteLock] writes applied, shared = " + shared.get());
    }

    // Semaphore: limit concurrent access to a resource (e.g. a connection pool).
    static void semaphoreDemo() throws InterruptedException {
        Semaphore permits = new Semaphore(2);   // only 2 at a time
        AtomicInteger maxConcurrent = new AtomicInteger();
        AtomicInteger current = new AtomicInteger();

        Thread[] ts = new Thread[6];
        for (int i = 0; i < ts.length; i++) {
            ts[i] = new Thread(() -> {
                try {
                    permits.acquire();
                    int now = current.incrementAndGet();
                    maxConcurrent.accumulateAndGet(now, Math::max);
                    Thread.sleep(20);
                    current.decrementAndGet();
                    permits.release();
                } catch (InterruptedException ignored) {}
            });
        }
        for (Thread t : ts) t.start();
        for (Thread t : ts) t.join();
        System.out.println("[Semaphore] max concurrent observed = " + maxConcurrent.get() + " (cap = 2)");
    }
}
