package pkg16advconcurrency;

/*
 * advconcurrency6JavaMemoryModel.java
 * -----------------------------------
 * Java Memory Model (JMM): visibility, ordering, and happens-before.
 *
 * DEFINITION:
 *   The JMM defines when writes by one thread are visible to another. Without
 *   proper synchronization, the CPU/cache/compiler can reorder or cache values
 *   in ways that break naive assumptions.
 *
 * KEY POINTS:
 *   - happens-before: if A hb B, then B sees all effects of A.
 *   - volatile: writes are visible immediately to other threads (no stale reads).
 *   - synchronized: unlock hb subsequent lock on same monitor.
 *   - final fields: safe publication after constructor completes.
 *   - Avoid double-checked locking without volatile (classic bug).
 */
public class advconcurrency6JavaMemoryModel {

    static class BrokenFlag {
        boolean ready = false;  // NOT volatile — may never be seen by reader
        int value = 0;
    }

    static class SafeFlag {
        volatile boolean ready = false;
        int value = 0;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("JMM rules (happens-before edges):");
        System.out.println("  - unlock -> lock (same monitor)");
        System.out.println("  - volatile write -> volatile read (same field)");
        System.out.println("  - thread start -> thread actions");
        System.out.println("  - thread actions -> thread join");

        SafeFlag safe = new SafeFlag();
        Thread writer = new Thread(() -> {
            safe.value = 42;
            safe.ready = true;   // volatile write — publishes value too
        });
        writer.start();
        writer.join();

        if (safe.ready) System.out.println("\nSafeFlag: ready=true, value=" + safe.value);

        // Demonstrate volatile vs non-volatile (may not fail on all JVMs — JMM allows stale read)
        BrokenFlag broken = new BrokenFlag();
        Thread w2 = new Thread(() -> {
            broken.value = 99;
            broken.ready = true;
        });
        Thread r2 = new Thread(() -> {
            while (!broken.ready) { /* spin */ }
            System.out.println("BrokenFlag (no volatile): value might be 0 or 99 -> " + broken.value);
        });
        w2.start(); r2.start();
        w2.join(); r2.join();

        System.out.println("\nRule: use volatile, synchronized, or atomic vars for cross-thread visibility.");
    }
}
