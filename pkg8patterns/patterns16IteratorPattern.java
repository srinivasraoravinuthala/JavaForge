package pkg8patterns;

/*
 * Iterator (Behavioral)
 * ---------------------
 * INTENT: provide a way to access elements of an aggregate sequentially without
 *         exposing its underlying representation.
 * UML: Iterable + iterator(): Iterator ; Iterator + hasNext()/next().
 * PROS: uniform traversal; multiple simultaneous iterations.
 * CONS: trivial for arrays/lists (built-in).
 * REAL-WORLD: java.util.Iterator, the for-each loop.
 */
import java.util.*;

public class patterns16IteratorPattern {

    // Custom aggregate with its own iterator
    static class RingBuffer<T> implements Iterable<T> {
        private final List<T> items = new ArrayList<>();
        void add(T item) { items.add(item); }

        public Iterator<T> iterator() {
            return new Iterator<>() {
                private int index = 0;
                public boolean hasNext() { return index < items.size(); }
                public T next() {
                    if (!hasNext()) throw new NoSuchElementException();
                    return items.get(index++);
                }
            };
        }
    }

    public static void main(String[] args) {
        RingBuffer<String> rb = new RingBuffer<>();
        rb.add("a"); rb.add("b"); rb.add("c");

        // Works with for-each because it is Iterable
        for (String s : rb) System.out.print(s + " ");
        System.out.println();

        // Manual iteration
        Iterator<String> it = rb.iterator();
        while (it.hasNext()) System.out.print(it.next().toUpperCase() + " ");
        System.out.println();
    }
}
