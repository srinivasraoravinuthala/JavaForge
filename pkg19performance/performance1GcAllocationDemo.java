package pkg19performance;

/*
 * performance1GcAllocationDemo.java
 * ---------------------------------
 * Allocation pressure and GC: observe how object churn triggers collection.
 *
 * DEFINITION:
 *   Short-lived objects live in Eden; minor GC clears them. Promoted long-lived
 *   objects fill Old gen and trigger major GC. Excessive allocation = GC overhead.
 *
 * KEY POINTS:
 *   - Prefer object reuse, pools, and primitives where hot.
 *   - -Xlog:gc* logs GC events (Java 9+ unified logging).
 *   - Run with: java -Xlog:gc:stdout performance1GcAllocationDemo.java
 */
public class performance1GcAllocationDemo {

    public static void main(String[] args) {
        System.out.println("Allocating 500k short-lived objects...");
        long before = System.nanoTime();
        for (int round = 0; round < 5; round++) {
            Object[] junk = new Object[100_000];
            for (int i = 0; i < junk.length; i++) junk[i] = new byte[64];
        }
        long ms = (System.nanoTime() - before) / 1_000_000;
        System.out.println("Done in " + ms + " ms");

        long heapUsed = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024;
        System.out.println("Heap used (approx): " + heapUsed + " KB");
        System.out.println("\nTip: rerun with -Xlog:gc:stdout to see GC activity");
    }
}
