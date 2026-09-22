package pkg2versions;

/*
 * versions4Java9To11Features.java
 * ----------------------
 * Cumulative Java 9-11 example. The whole file requires Java 11.
 * It is not a Java 9 or Java 10 program: var is Java 10, and the
 * String methods below are Java 11. Collection uses Collectors.toList(),
 * which is Java 8. It does not call the Java 16 Stream toList method.
 *
 * FEATURES & WHY:
 *  Java 9  : Module system (JPMS), collection factory methods (List.of),
 *            private interface methods, Stream.takeWhile/dropWhile.
 *  Java 10 : `var` local variable type inference.
 *  Java 11 : new String methods (strip, isBlank, lines, repeat),
 *            Files.readString/writeString, the standard HttpClient, run .java directly.
 *  (Java 11 is an LTS release.)
 */
import java.util.*;
import java.util.stream.*;

public class versions4Java9To11Features {
    public static void main(String[] args) {
        // Java 9: immutable collection factories
        List<Integer> list = List.of(1, 2, 3);
        Map<String, Integer> map = Map.of("a", 1, "b", 2);
        Set<String> set = Set.of("x", "y");
        System.out.println("factories: " + list + " " + map + " " + set);

        // Java 9: takeWhile / dropWhile
        List<Integer> taken = Stream.of(1, 2, 3, 4, 1).takeWhile(x -> x < 4).collect(Collectors.toList());
        List<Integer> dropped = Stream.of(1, 2, 3, 4, 1).dropWhile(x -> x < 4).collect(Collectors.toList());
        System.out.println("takeWhile<4: " + taken + " dropWhile<4: " + dropped);

        // Java 10: var
        var greeting = "hello";
        var numbers = new ArrayList<Integer>();
        numbers.add(42);
        System.out.println("var: " + greeting + " " + numbers);

        // Java 11: String methods
        System.out.println("isBlank: " + "   ".isBlank());
        System.out.println("strip: [" + "  hi  ".strip() + "]");
        System.out.println("repeat: " + "=".repeat(10));
        "line1\nline2\nline3".lines().forEach(l -> System.out.println("  " + l));
    }
}
