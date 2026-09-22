package pkg5leetcode.blind75;

/*
 * Longest Repeating Character Replacement | LC 424
 * APPROACH: Sliding window; shrink when window - maxFreq > k.
 * COMPLEXITY: Time O(n), Space O(26)
 */
public class blind75_LC424LongestRepeatingCharacterReplacement {
    static int characterReplacement(String s, int k) {
        int[] count = new int[26];
        int left = 0, maxFreq = 0, best = 0;
        for (int right = 0; right < s.length(); right++) {
            maxFreq = Math.max(maxFreq, ++count[s.charAt(right) - 'A']);
            while (right - left + 1 - maxFreq > k) count[s.charAt(left++) - 'A']--;
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        check(characterReplacement("ABAB", 2) == 4, "case1");
        check(characterReplacement("AABABBA", 1) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
