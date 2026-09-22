package pkg1core;

/*
 * core28ComparatorDemo.java
 * -------------------------
 * Comparable (natural order) vs Comparator (custom order); chaining comparators.
 *
 * EXPLANATION:
 *  - Comparable: built into the class (`compareTo`). One natural ordering.
 *  - Comparator: external, multiple orderings, lambdas/method refs.
 *  - Use `Comparator.comparing`, `thenComparing`, `reversed`, `nullsFirst/Last`.
 */
import java.util.*;

public class core28ComparatorDemo {

    record Student(String name, int score, int age) implements Comparable<Student> {
        @Override
        public int compareTo(Student o) {
            return Integer.compare(this.score, o.score);   // natural: by score ascending
        }
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(List.of(
                new Student("Ana", 88, 20),
                new Student("Bob", 92, 19),
                new Student("Cara", 88, 21)));

        // Natural order (Comparable)
        Collections.sort(list);
        System.out.println("by score (Comparable): " + list);

        // Custom Comparator: score desc, then age asc
        list.sort(Comparator
                .comparingInt(Student::score).reversed()
                .thenComparingInt(Student::age));
        System.out.println("score desc, age asc:   " + list);

        // Comparator.comparing with null-safe name
        list.sort(Comparator.comparing(Student::name, Comparator.nullsLast(String::compareTo)));
        System.out.println("by name:               " + list);
    }
}
