package pkg5leetcode.blind75;

/*
 * Longest Palindromic Substring | LC 5
 * APPROACH: Expand around center for odd/even lengths.
 * COMPLEXITY: Time O(n^2), Space O(1)
 */
public class blind75_LC5LongestPalindromicSubstring {
    static String longestPalindrome(String s) {
        int start = 0, maxLen = 0;
        for (int i = 0; i < s.length(); i++) {
            int[] odd = expand(s, i, i);
            int[] even = expand(s, i, i + 1);
            int len = Math.max(odd[1] - odd[0], even[1] - even[0]);
            if (len > maxLen) {
                maxLen = len;
                start = len == odd[1] - odd[0] ? odd[0] : even[0];
            }
        }
        return s.substring(start, start + maxLen);
    }

    static int[] expand(String s, int lo, int hi) {
        while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) { lo--; hi++; }
        return new int[]{lo + 1, hi};
    }

    public static void main(String[] args) {
        check(longestPalindrome("babad").equals("bab") || longestPalindrome("babad").equals("aba"), "case1");
        check(longestPalindrome("cbbd").equals("bb"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
