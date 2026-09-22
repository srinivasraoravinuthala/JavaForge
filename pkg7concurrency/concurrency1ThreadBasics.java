package pkg7concurrency;

/*
 * concurrency1ThreadBasics.java
 * -----------------
 * Creating threads (Runnable vs subclassing), start vs run, join, daemon threads.
 *
 * KEY POINTS:
 *   - Prefer implementing Runnable (composition) over extending Thread.
 *   - start() spawns a new thread; run() just calls the method on the current thread.
 *   - join() waits for a thread to finish.
 *   - Daemon threads don't keep the JVM alive.
 */
public class concurrency1ThreadBasics {

    public static void main(String[] args) throws InterruptedException {
        // 1) Runnable (preferred)
        Runnable task = () -> System.out.println("  running on: " + Thread.currentThread().getName());
        Thread t1 = new Thread(task, "worker-1");
        t1.start();
        t1.join();                         // wait for t1 to complete

        // 2) start() vs run()
        System.out.println("\nrun() executes on caller thread:");
        new Thread(task, "worker-2").run();    // NOTE: no new thread; runs on main

        // 3) Multiple threads and join all
        System.out.println("\nLaunching 3 threads:");
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            int id = i;
            threads[i] = new Thread(() -> System.out.println("  thread " + id + " did work"));
            threads[i].start();
        }
        for (Thread t : threads) t.join();

        // 4) Daemon thread (background; won't block JVM shutdown)
        Thread daemon = new Thread(() -> {
            while (true) { try { Thread.sleep(100); } catch (InterruptedException e) { return; } }
        });
        daemon.setDaemon(true);
        daemon.start();
        System.out.println("\nDaemon alive: " + daemon.isAlive() + " (JVM can exit without waiting for it)");

        System.out.println("main thread finishing");
    }
}
