package pkg1core;

/*
 * core9StringsDemo.java
 * ----------------
 * String immutability, common methods, StringBuilder, text blocks, formatting.
 *
 * EXPLANATION:
 *  - Strings are immutable: every "modification" creates a new String.
 *  - Literals are interned in the string pool; `new String("x")` is a new object.
 *  - Use StringBuilder for repeated concatenation (avoids creating many objects).
 */
public class core9StringsDemo {
    public static void main(String[] args) {
        String s = "Java Mastery";

        // Common methods
        System.out.println("length: " + s.length());
        System.out.println("upper: " + s.toUpperCase());
        System.out.println("substring(5): " + s.substring(5));
        System.out.println("indexOf('M'): " + s.indexOf('M'));
        System.out.println("replace: " + s.replace("Java", "Pro"));
        System.out.println("split: " + java.util.Arrays.toString(s.split(" ")));
        System.out.println("repeat: " + "ab".repeat(3));
        System.out.println("strip/isBlank: [" + "  hi  ".strip() + "] " + "   ".isBlank());

        // Immutability proof
        String original = "abc";
        original.toUpperCase();         // result ignored -> original unchanged
        System.out.println("still lower: " + original);

        // == vs equals (pool vs new object)
        String a = "hello";
        String b = "hello";             // same pooled object
        String c = new String("hello"); // new heap object
        System.out.println("a == b : " + (a == b) + " (pooled)");
        System.out.println("a == c : " + (a == c) + " (different objects)");
        System.out.println("a.equals(c): " + a.equals(c) + " (value equality)");

        // StringBuilder for efficient building
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 5; i++) sb.append(i).append(',');
        sb.setLength(sb.length() - 1);  // drop trailing comma
        System.out.println("built: " + sb);
        System.out.println("reversed: " + sb.reverse());

        // Text block (Java 15+) and formatting
        String json = """
            {
              "name": "JavaMastery",
              "level": 21
            }""";
        System.out.println("text block:\n" + json);
        System.out.println("formatted: " + "Pi is approximately %.3f".formatted(Math.PI));
    }
}
