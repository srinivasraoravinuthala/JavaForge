package pkg5leetcode.interview150;

/*
 * Word Pattern | LC 290
 * APPROACH: Bidirectional map pattern char to word and back.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class interview150_LC290WordPattern {
    static boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if (pattern.length() != words.length) return false;
        Map<Character, String> ps = new HashMap<>();
        Map<String, Character> sp = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if (ps.containsKey(c) && !ps.get(c).equals(w)) return false;
            if (sp.containsKey(w) && sp.get(w) != c) return false;
            ps.put(c, w);
            sp.put(w, c);
        }
        return true;
    }

    public static void main(String[] args) {
        check(wordPattern("abba", "dog cat cat dog"), "case1");
        check(!wordPattern("abba", "dog cat cat fish"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
