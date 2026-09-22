package pkg5leetcode.blind75;

/*
 * Longest Common Subsequence | LC 1143
 * APPROACH: 2D DP on character prefixes of both strings.
 * COMPLEXITY: Time O(mn), Space O(mn)
 */
public class blind75_LC1143LongestCommonSubsequence {
    static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        return dp[m][n];
    }

    public static void main(String[] args) {
        check(longestCommonSubsequence("abcde", "ace") == 3, "case1");
        check(longestCommonSubsequence("abc", "abc") == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
