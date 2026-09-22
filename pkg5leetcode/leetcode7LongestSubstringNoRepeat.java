package pkg5leetcode;

/*
 * LeetCode 3: Longest Substring Without Repeating Characters  (Medium)
 * -------------------------------------------------------------------
 * Find the length of the longest substring without repeating characters.
 *
 * APPROACH: sliding window + last-seen index map. Move left past the last
 * duplicate when a repeat is found inside the window.
 * COMPLEXITY: Time O(n), Space O(min(n, charset)).
 */
import java.util.*;

public class leetcode7LongestSubstringNoRepeat {

    static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> last = new HashMap<>();
        int left = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (last.containsKey(c) && last.get(c) >= left) {
                left = last.get(c) + 1;        // shrink window past the duplicate
            }
            last.put(c, right);
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        check(lengthOfLongestSubstring("abcabcbb") == 3, "abcabcbb");
        check(lengthOfLongestSubstring("bbbbb") == 1, "bbbbb");
        check(lengthOfLongestSubstring("pwwkew") == 3, "pwwkew");
        check(lengthOfLongestSubstring("") == 0, "empty");
        check(lengthOfLongestSubstring("dvdf") == 3, "dvdf");
        System.out.println("leetcode7LongestSubstringNoRepeat: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
