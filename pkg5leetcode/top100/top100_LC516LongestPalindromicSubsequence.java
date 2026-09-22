package pkg5leetcode.top100;

/*
 * Longest Palindromic Subsequence | LC 516
 * APPROACH: DP on substrings: match or best of excluding ends.
 * COMPLEXITY: Time O(n^2), Space O(n^2)
 */
public class top100_LC516LongestPalindromicSubsequence {
    static int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }

    public static void main(String[] args) {
        check(longestPalindromeSubseq("bbbab") == 4, "case1");
        check(longestPalindromeSubseq("cbbd") == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
