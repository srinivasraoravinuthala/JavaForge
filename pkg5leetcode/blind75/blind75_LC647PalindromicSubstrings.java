package pkg5leetcode.blind75;

/*
 * Palindromic Substrings | LC 647
 * APPROACH: Expand around each center counting palindromes.
 * COMPLEXITY: Time O(n^2), Space O(1)
 */
public class blind75_LC647PalindromicSubstrings {
    static int countSubstrings(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count += expand(s, i, i);
            count += expand(s, i, i + 1);
        }
        return count;
    }

    static int expand(String s, int lo, int hi) {
        int c = 0;
        while (lo >= 0 && hi < s.length() && s.charAt(lo) == s.charAt(hi)) { lo--; hi++; c++; }
        return c;
    }

    public static void main(String[] args) {
        check(countSubstrings("abc") == 3, "case1");
        check(countSubstrings("aaa") == 6, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
