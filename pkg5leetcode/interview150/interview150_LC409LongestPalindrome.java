package pkg5leetcode.interview150;

/*
 * Longest Palindrome | LC 409
 * APPROACH: Use pairs of chars plus one center if odd count exists.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC409LongestPalindrome {
    static int longestPalindrome(String s) {
        int[] cnt = new int[128];
        for (char c : s.toCharArray()) cnt[c]++;
        int len = 0, odd = 0;
        for (int x : cnt) {
            len += x / 2 * 2;
            if (x % 2 == 1) odd = 1;
        }
        return len + odd;
    }

    public static void main(String[] args) {
        check(longestPalindrome("abccccdd") == 7, "case1");
        check(longestPalindrome("a") == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
