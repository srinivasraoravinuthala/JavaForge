package pkg4algorithms;

/*
 * algorithms4DynamicProgramming.java
 * -----------------------
 * DP = solve overlapping subproblems once and reuse (memoization / tabulation).
 * Two requirements: optimal substructure + overlapping subproblems.
 *
 * Covered: Fibonacci, 0/1 knapsack, LCS, coin change (min coins), LIS, edit distance.
 */
import java.util.*;

public class algorithms4DynamicProgramming {

    // Fibonacci - bottom-up, O(n) time O(1) space
    static long fib(int n) {
        if (n < 2) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) { long c = a + b; a = b; b = c; }
        return b;
    }

    // 0/1 Knapsack - max value within capacity, O(n*W)
    static int knapsack(int W, int[] wt, int[] val) {
        int n = wt.length;
        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++)
            for (int w = 0; w <= W; w++) {
                dp[i][w] = dp[i - 1][w];                        // skip item i
                if (wt[i - 1] <= w)                             // take item i
                    dp[i][w] = Math.max(dp[i][w], val[i - 1] + dp[i - 1][w - wt[i - 1]]);
            }
        return dp[n][W];
    }

    // Longest Common Subsequence, O(m*n)
    static int lcs(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                dp[i][j] = a.charAt(i - 1) == b.charAt(j - 1)
                        ? dp[i - 1][j - 1] + 1
                        : Math.max(dp[i - 1][j], dp[i][j - 1]);
        return dp[m][n];
    }

    // Coin change - minimum number of coins for amount (DP), O(amount*coins)
    static int coinChangeMin(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int a = 1; a <= amount; a++)
            for (int c : coins)
                if (c <= a) dp[a] = Math.min(dp[a], dp[a - c] + 1);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // Longest Increasing Subsequence, O(n^2) (O(n log n) version possible)
    static int lis(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int best = 1;
        for (int i = 1; i < nums.length; i++)
            for (int j = 0; j < i; j++)
                if (nums[j] < nums[i]) { dp[i] = Math.max(dp[i], dp[j] + 1); best = Math.max(best, dp[i]); }
        return best;
    }

    // Edit (Levenshtein) distance, O(m*n)
    static int editDistance(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                dp[i][j] = a.charAt(i - 1) == b.charAt(j - 1)
                        ? dp[i - 1][j - 1]
                        : 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println("fib(40) = " + fib(40));
        System.out.println("knapsack = " + knapsack(50, new int[]{10, 20, 30}, new int[]{60, 100, 120}));
        System.out.println("lcs(ABCBDAB, BDCAB) = " + lcs("ABCBDAB", "BDCAB"));
        System.out.println("coinChangeMin(11, {1,2,5}) = " + coinChangeMin(11, new int[]{1, 2, 5}));
        System.out.println("lis([10,9,2,5,3,7,101,18]) = " + lis(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
        System.out.println("editDistance(horse, ros) = " + editDistance("horse", "ros"));
    }
}
