package pkg5leetcode.blind75;

/*
 * Unique Paths | LC 62
 * APPROACH: Grid DP paths[i][j] = paths[i-1][j] + paths[i][j-1].
 * COMPLEXITY: Time O(mn), Space O(n)
 */
public class blind75_LC62UniquePaths {
    static int uniquePaths(int m, int n) {
        int[] row = new int[n];
        java.util.Arrays.fill(row, 1);
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                row[j] += row[j - 1];
        return row[n - 1];
    }

    public static void main(String[] args) {
        check(uniquePaths(3, 7) == 28, "case1");
        check(uniquePaths(3, 2) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
