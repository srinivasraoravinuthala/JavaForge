package pkg5leetcode.interview150;

/*
 * Find All Anagrams in a String | LC 438
 * APPROACH: Sliding window with char frequency match count.
 * COMPLEXITY: Time O(n), Space O(1)
 */
import java.util.*;

public class interview150_LC438FindAllAnagramsInAString {
    static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() > s.length()) return res;
        int[] need = new int[26], have = new int[26];
        for (char c : p.toCharArray()) need[c - 'a']++;
        for (int i = 0; i < s.length(); i++) {
            have[s.charAt(i) - 'a']++;
            if (i >= p.length()) have[s.charAt(i - p.length()) - 'a']--;
            if (i >= p.length() - 1 && Arrays.equals(need, have)) res.add(i - p.length() + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        check(findAnagrams("cbaebabacd", "abc").equals(Arrays.asList(0, 6)), "case1");
        check(findAnagrams("abab", "ab").equals(Arrays.asList(0, 1, 2)), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
