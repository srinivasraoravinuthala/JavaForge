package pkg5leetcode.blind75;

/*
 * Longest Substring Without Repeating Characters | LC 3
 * APPROACH: Sliding window with last-seen index map.
 * COMPLEXITY: Time O(n), Space O(min(n, alphabet))
 */
import java.util.*;

public class blind75_LC3LongestSubstringWithoutRepeating {
    static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> last = new HashMap<>();
        int best = 0, left = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (last.containsKey(c) && last.get(c) >= left) left = last.get(c) + 1;
            last.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        check(lengthOfLongestSubstring("abcabcbb") == 3, "case1");
        check(lengthOfLongestSubstring("bbbbb") == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
