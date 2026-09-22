package pkg6jvm;

/*
 * jvm2MemoryAreasDemo.java
 * --------------------
 * Inspect the JVM runtime memory and demonstrate stack vs heap behavior.
 *
 * RUNTIME DATA AREAS:
 *   - Heap        : objects & arrays (shared, GC-managed). Young + Old generations.
 *   - Stack       : per-thread frames (locals, operand stack). StackOverflowError.
 *   - Metaspace   : class metadata (native memory, replaces PermGen since Java 8).
 *   - PC register : current instruction per thread.
 *   - Native stack: for JNI calls.
 *
 * Try GC behavior with flags, e.g.:
 *   java -Xms64m -Xmx256m -XX:+UseG1GC -verbose:gc jvm2MemoryAreasDemo.java
 */
public class jvm2MemoryAreasDemo {

    // Instance fields live inside the object on the HEAP
    static class Box { int value; int[] data = new int[1000]; }

    // Each recursive call adds a frame to the STACK
    static int recurse(int depth) {
        return recurse(depth + 1);     // intentionally unbounded to hit the stack limit
    }

    public static void main(String[] args) {
        Runtime rt = Runtime.getRuntime();
        long mb = 1024 * 1024;
        System.out.println("=== Heap (Runtime) ===");
        System.out.println("max   : " + rt.maxMemory() / mb + " MB");
        System.out.println("total : " + rt.totalMemory() / mb + " MB");
        System.out.println("free  : " + rt.freeMemory() / mb + " MB");

        // Allocate objects on the heap and watch free memory drop
        System.out.println("\nAllocating 10,000 boxes on the heap...");
        Box[] boxes = new Box[10_000];
        for (int i = 0; i < boxes.length; i++) boxes[i] = new Box();
        System.out.println("free after alloc : " + rt.freeMemory() / mb + " MB");

        // Release references and suggest GC
        boxes = null;
        System.gc();                                   // a hint, not a guarantee
        System.out.println("free after gc    : " + rt.freeMemory() / mb + " MB (objects became unreachable)");

        // Demonstrate the stack limit safely
        System.out.println("\nForcing deep recursion to hit the stack limit...");
        try {
            recurse(0);
        } catch (StackOverflowError e) {
            System.out.println("Caught StackOverflowError -> the call stack is finite (per-thread).");
        }

        System.out.println("\nMetaspace holds class metadata; loaded classes ~ " +
                ManagementHint());
    }

    // Avoid extra deps: just report number of loaded classes via a simple probe.
    static String ManagementHint() {
        return "(use 'jcmd <pid> VM.metaspace' or -verbose:class to inspect Metaspace)";
    }
}
