package pkg2versions;

/*
 * versions6Java21Features.java  (2023, LTS) -- the target version for this project
 * ----------------------------------------------------------------------
 * FEATURES & WHY:
 *  - Virtual Threads (JEP 444)     : cheap threads for massive concurrent blocking I/O.
 *  - Pattern matching for switch    : exhaustive, type-based branching (final).
 *  - Record patterns (JEP 440)      : deconstruct records in switch/instanceof.
 *  - Sequenced collections (JEP 431): getFirst/getLast/reversed on ordered collections.
 *  - String templates, structured concurrency, and scoped values were previews
 *    (not shown here). This file requires Java 21. It is not a Java 19 program.
 */
import java.util.*;

public class versions6Java21Features {

    sealed interface Shape permits Circle, Rect {}
    record Circle(double r) implements Shape {}
    record Rect(double w, double h) implements Shape {}

    static String describe(Object o) {
        // Record patterns + guards + exhaustive switch
        return switch (o) {
            case Circle(double r) when r > 10 -> "big circle r=" + r;
            case Circle(double r)             -> "circle r=" + r;
            case Rect(double w, double h)     -> "rect " + w + "x" + h;
            case null                          -> "null";
            default                            -> "other";
        };
    }

    public static void main(String[] args) throws InterruptedException {
        // Record patterns
        System.out.println(describe(new Circle(3)));
        System.out.println(describe(new Circle(20)));
        System.out.println(describe(new Rect(2, 5)));
        System.out.println(describe(null));

        // Sequenced collections (Java 21)
        SequencedCollection<Integer> seq = new ArrayList<>(List.of(1, 2, 3, 4));
        System.out.println("first=" + seq.getFirst() + " last=" + seq.getLast());
        System.out.println("reversed=" + seq.reversed());

        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();
        lhm.put("a", 1); lhm.put("b", 2); lhm.put("c", 3);
        System.out.println("firstEntry=" + lhm.firstEntry() + " lastEntry=" + lhm.lastEntry());

        // Virtual threads: launch many cheap threads
        long start = System.currentTimeMillis();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int id = i;
            Thread t = Thread.ofVirtual().start(() -> {
                try { Thread.sleep(10); } catch (InterruptedException ignored) {}
            });
            threads.add(t);
        }
        for (Thread t : threads) t.join();
        System.out.println("1000 virtual threads finished in " + (System.currentTimeMillis() - start) + "ms");
    }
}
