package pkg5leetcode.official75;

/*
 * Unique Paths II | LC 63
 * APPROACH: DP paths to cell avoiding obstacles.
 * COMPLEXITY: Time O(mn), Space O(n)
 */
public class official75_LC63UniquePathsII {
    static int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1) return 0;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (grid[i][j] == 1) dp[j] = 0;
                else if (j > 0) dp[j] += dp[j - 1];
        return dp[n - 1];
    }

    public static void main(String[] args) {
        check(uniquePathsWithObstacles(new int[][]{{0,0,0},{0,1,0},{0,0,0}}) == 2, "case1");
        check(uniquePathsWithObstacles(new int[][]{{0,1},{0,0}}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
