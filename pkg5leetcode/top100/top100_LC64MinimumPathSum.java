package pkg5leetcode.top100;

/*
 * Minimum Path Sum | LC 64
 * APPROACH: DP accumulate min path from top-left to each cell.
 * COMPLEXITY: Time O(m*n), Space O(1)
 */
public class top100_LC64MinimumPathSum {
    static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue;
                if (r == 0) grid[r][c] += grid[r][c - 1];
                else if (c == 0) grid[r][c] += grid[r - 1][c];
                else grid[r][c] += Math.min(grid[r - 1][c], grid[r][c - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        check(minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}) == 7, "case1");
        check(minPathSum(new int[][]{{1, 2, 3}, {4, 5, 6}}) == 12, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
