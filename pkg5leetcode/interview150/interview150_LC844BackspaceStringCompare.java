package pkg5leetcode.interview150;

/*
 * Backspace String Compare | LC 844
 * APPROACH: Build strings processing backspace from end with stack logic.
 * COMPLEXITY: Time O(n), Space O(n)
 */
public class interview150_LC844BackspaceStringCompare {
    static String build(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '#') { if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1); }
            else sb.append(c);
        }
        return sb.toString();
    }

    static boolean backspaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    public static void main(String[] args) {
        check(backspaceCompare("ab#c", "ad#c"), "case1");
        check(!backspaceCompare("ab##", "c#d#"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
