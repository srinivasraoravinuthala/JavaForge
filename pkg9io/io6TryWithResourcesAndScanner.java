package pkg9io;

import java.io.IOException;
import java.util.Scanner;

/*
 * io6TryWithResourcesAndScanner.java
 * ----------------------------------
 * Deterministic resource cleanup (try-with-resources) and parsing with Scanner.
 *
 * DEFINITION:
 *   try-with-resources auto-closes anything implementing AutoCloseable when the
 *   block exits (normally or via exception). Scanner tokenizes text/streams into
 *   typed values (ints, words, lines).
 *
 * KEY POINTS:
 *   - Resources close in REVERSE order of declaration, before catch/finally.
 *   - Exceptions during close are added as "suppressed" to the primary exception.
 *   - Implement AutoCloseable to make your own types usable in try-with-resources.
 *   - Scanner over a String is great for quick parsing without files.
 */
public class io6TryWithResourcesAndScanner {

    // Custom resource showing the auto-close hook
    static class Resource implements AutoCloseable {
        final String id;
        Resource(String id) { this.id = id; System.out.println("  open  " + id); }
        void use()          { System.out.println("  use   " + id); }
        @Override public void close() { System.out.println("  close " + id); }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Resources close in reverse order:");
        try (Resource a = new Resource("A");
             Resource b = new Resource("B")) {     // B closes before A
            a.use();
            b.use();
        }

        // Scanner: parse mixed tokens from a String
        System.out.println("\nScanner parsing tokens:");
        try (Scanner sc = new Scanner("Ada 36 3.14 true")) {
            System.out.println("  word   = " + sc.next());
            System.out.println("  int    = " + sc.nextInt());
            System.out.println("  double = " + sc.nextDouble());
            System.out.println("  bool   = " + sc.nextBoolean());
        }

        // Scanner reading lines, with a custom delimiter
        System.out.println("\nCSV via delimiter:");
        try (Scanner sc = new Scanner("red,green,blue").useDelimiter(",")) {
            while (sc.hasNext()) System.out.println("  color = " + sc.next());
        }
        // Note: reading interactive input would be: new Scanner(System.in)
    }
}
