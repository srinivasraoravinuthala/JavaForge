package pkg13libs;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * libs2RegexPattern.java
 * ----------------------
 * Regular expressions with java.util.regex (Pattern + Matcher).
 *
 * DEFINITION:
 *   A regex is a pattern that describes a set of strings. Pattern compiles it;
 *   Matcher applies it to input to test, find, extract groups, or replace.
 *
 * KEY POINTS:
 *   - Compile a Pattern once and reuse it (compilation is not free).
 *   - matches() = whole string; find() = next match anywhere; group(n) = captures.
 *   - Use named groups (?<name>...) for readability.
 *   - String.matches/replaceAll are handy shortcuts for one-off uses.
 */
public class libs2RegexPattern {

    public static void main(String[] args) {
        // 1) Validate with a full match
        Pattern email = Pattern.compile("^[\\w.+-]+@[\\w-]+\\.[a-z]{2,}$");
        for (String s : new String[]{"ada@java.org", "not-an-email"})
            System.out.printf("%-15s valid email? %s%n", s, email.matcher(s).matches());

        // 2) Find all matches
        System.out.println("\nNumbers found:");
        Matcher m = Pattern.compile("\\d+").matcher("order 12, item 345, qty 6");
        while (m.find()) System.out.println("  " + m.group() + " at index " + m.start());

        // 3) Capture groups (named) — parse a date
        Pattern date = Pattern.compile("(?<y>\\d{4})-(?<m>\\d{2})-(?<d>\\d{2})");
        Matcher dm = date.matcher("Release: 2026-06-14");
        if (dm.find())
            System.out.printf("%nParsed date: year=%s month=%s day=%s%n",
                    dm.group("y"), dm.group("m"), dm.group("d"));

        // 4) Replace using backreferences
        String masked = "card 4111 1111 1111 1234".replaceAll("\\d{4}(?= \\d{4})", "****");
        System.out.println("\nMasked: " + masked);

        // 5) Split on a regex
        System.out.println("\nSplit: " + java.util.Arrays.toString("a, b ,c ,  d".split("\\s*,\\s*")));
    }
}
