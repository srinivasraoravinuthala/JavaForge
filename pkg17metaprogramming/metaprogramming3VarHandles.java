package pkg17metaprogramming;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * metaprogramming3VarHandles.java
 * ---------------------------------
 * VarHandle: atomic and ordered access to fields/arrays (Java 9+).
 *
 * DEFINITION:
 *   VarHandle is the modern replacement for many sun.misc.Unsafe field ops.
 *   It supports plain, volatile, and CAS (compare-and-set) access modes.
 *
 * KEY POINTS:
 *   - findVarHandle(Class, name, type) locates a field.
 *   - compareAndSet, getAndAdd, setRelease — building blocks for lock-free code.
 *   - AtomicInteger uses similar primitives internally.
 */
public class metaprogramming3VarHandles {

    static class Counter {
        volatile int count = 0;
    }

    public static void main(String[] args) throws Throwable {
        Counter c = new Counter();
        VarHandle vh = MethodHandles.lookup().findVarHandle(Counter.class, "count", int.class);

        vh.set(c, 10);
        System.out.println("set -> " + vh.get(c));

        boolean swapped = vh.compareAndSet(c, 10, 99);
        System.out.println("CAS 10->99: " + swapped + ", count=" + vh.get(c));

        int added = (int) vh.getAndAdd(c, 5);
        System.out.println("getAndAdd(5): previous=" + added + ", now=" + vh.get(c));

        // Compare with AtomicInteger (same idea, higher-level API)
        AtomicInteger ai = new AtomicInteger(0);
        ai.compareAndSet(0, 42);
        System.out.println("\nAtomicInteger CAS -> " + ai.get());
    }
}
