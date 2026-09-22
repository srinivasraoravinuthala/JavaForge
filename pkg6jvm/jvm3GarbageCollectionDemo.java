package pkg6jvm;

/*
 * jvm3GarbageCollectionDemo.java
 * --------------------------
 * Demonstrates reachability, reference types, and GC notifications.
 *
 * KEY IDEAS:
 *   - An object is eligible for GC when no GC root can reach it.
 *   - Reference strength: Strong > Soft > Weak > Phantom.
 *       Strong : never collected while reachable.
 *       Soft   : collected only under memory pressure (good for caches).
 *       Weak   : collected at next GC if only weakly reachable (WeakHashMap).
 *       Phantom: for post-mortem cleanup via a ReferenceQueue (use Cleaner).
 *   - finalize() is deprecated; prefer try-with-resources / java.lang.ref.Cleaner.
 *
 * GC ALGORITHMS (choose with flags; see docs/JVMInternals.md):
 *   -XX:+UseSerialGC | -XX:+UseParallelGC | -XX:+UseG1GC | -XX:+UseZGC | -XX:+UseShenandoahGC
 */
import java.lang.ref.*;
import java.util.*;

public class jvm3GarbageCollectionDemo {

    public static void main(String[] args) throws InterruptedException {
        // 1) Eligibility: dropping the only reference makes the object collectable
        Object o = new Object();
        System.out.println("strong ref held: " + (o != null));
        o = null;                          // now unreachable -> eligible for GC
        System.out.println("reference cleared -> object now eligible for GC");

        // 2) WeakReference: cleared by GC when only weakly reachable
        Object strong = new String("cacheable");
        WeakReference<Object> weak = new WeakReference<>(strong);
        System.out.println("\nweak.get() before clearing strong: " + weak.get());
        strong = null;
        System.gc();
        Thread.sleep(50);                  // give GC a moment
        System.out.println("weak.get() after gc (likely null): " + weak.get());

        // 3) WeakHashMap: entries vanish when keys are no longer strongly referenced
        Map<Object, String> cache = new WeakHashMap<>();
        Object key = new Object();
        cache.put(key, "value");
        System.out.println("\nWeakHashMap size with key held: " + cache.size());
        key = null;
        System.gc();
        Thread.sleep(50);
        System.out.println("WeakHashMap size after key cleared: " + cache.size());

        // 4) PhantomReference + ReferenceQueue: detect when an object is collected
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        Object phantomTarget = new Object();
        PhantomReference<Object> phantom = new PhantomReference<>(phantomTarget, queue);
        phantomTarget = null;
        System.gc();
        Thread.sleep(50);
        Reference<?> collected = queue.poll();
        System.out.println("\nPhantom enqueued (object collected): " + (collected == phantom));

        System.out.println("\nTip: run with -verbose:gc to see GC events.");
    }
}
