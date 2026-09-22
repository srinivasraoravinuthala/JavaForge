package pkg5leetcode.interview150;

/*
 * Isomorphic Strings | LC 205
 * APPROACH: Two hash maps enforce one-to-one char mapping.
 * COMPLEXITY: Time O(n), Space O(1)
 */
import java.util.*;

public class interview150_LC205IsomorphicStrings {
    static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> st = new HashMap<>();
        Map<Character, Character> ts = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i), b = t.charAt(i);
            if (st.containsKey(a) && st.get(a) != b) return false;
            if (ts.containsKey(b) && ts.get(b) != a) return false;
            st.put(a, b);
            ts.put(b, a);
        }
        return true;
    }

    public static void main(String[] args) {
        check(isIsomorphic("egg", "add"), "case1");
        check(!isIsomorphic("foo", "bar"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
