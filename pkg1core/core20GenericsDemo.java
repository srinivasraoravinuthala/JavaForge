package pkg1core;

/*
 * core20GenericsDemo.java
 * -----------------
 * Generic classes/methods, bounded type parameters, wildcards (PECS).
 *
 * EXPLANATION:
 *  - Generics give compile-time type safety and remove casts.
 *  - Type erasure: generic type info is removed at runtime.
 *  - PECS: Producer Extends, Consumer Super.
 *      * `? extends T` to READ (produce) Ts.
 *      * `? super T`   to WRITE (consume) Ts.
 */
import java.util.*;

public class core20GenericsDemo {

    // Generic class (a simple immutable pair)
    static class Pair<A, B> {
        final A first; final B second;
        Pair(A a, B b){ first = a; second = b; }
        public String toString(){ return "(" + first + ", " + second + ")"; }
    }

    // Generic method with a bounded type parameter
    static <T extends Comparable<T>> T max(List<T> items) {
        T best = items.get(0);
        for (T x : items) if (x.compareTo(best) > 0) best = x;
        return best;
    }

    // PRODUCER: read from a source of "? extends Number"
    static double sum(List<? extends Number> nums) {
        double s = 0;
        for (Number n : nums) s += n.doubleValue();
        return s;
    }

    // CONSUMER: write Integers into "? super Integer"
    static void addInts(List<? super Integer> sink, int count) {
        for (int i = 1; i <= count; i++) sink.add(i);
    }

    public static void main(String[] args) {
        Pair<String, Integer> p = new Pair<>("age", 30);
        System.out.println("Pair: " + p);

        System.out.println("max([3,9,5,7]) = " + max(List.of(3, 9, 5, 7)));
        System.out.println("max(words)     = " + max(List.of("apple", "pear", "kiwi")));

        System.out.println("sum(ints)    = " + sum(List.of(1, 2, 3)));
        System.out.println("sum(doubles) = " + sum(List.of(1.5, 2.5)));

        List<Number> sink = new ArrayList<>();
        addInts(sink, 3);
        System.out.println("consumer sink: " + sink);
    }
}
