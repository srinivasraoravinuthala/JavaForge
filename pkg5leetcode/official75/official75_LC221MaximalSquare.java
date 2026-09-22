package pkg5leetcode.official75;

/*
 * Maximal Square | LC 221
 * APPROACH: DP side length of largest square ending at cell.
 * COMPLEXITY: Time O(mn), Space O(mn)
 */
public class official75_LC221MaximalSquare {
    static int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int best = 0;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    best = Math.max(best, dp[i][j]);
                }
        return best * best;
    }

    public static void main(String[] args) {
        check(maximalSquare(new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}}) == 4, "case1");
        check(maximalSquare(new char[][]{{'0','1'},{'1','0'}}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
