package pkg5leetcode.interview150;

/*
 * Triangle | LC 120
 * APPROACH: Bottom-up DP min path sum using next row.
 * COMPLEXITY: Time O(n^2), Space O(n)
 */
public class interview150_LC120Triangle {
    static int minimumTotal(java.util.List<java.util.List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) dp[i] = triangle.get(n - 1).get(i);
        for (int r = n - 2; r >= 0; r--) {
            for (int c = 0; c <= r; c++)
                dp[c] = triangle.get(r).get(c) + Math.min(dp[c], dp[c + 1]);
        }
        return dp[0];
    }

    public static void main(String[] args) {
        java.util.List<java.util.List<Integer>> t = java.util.Arrays.asList(
            java.util.Arrays.asList(2),
            java.util.Arrays.asList(3, 4),
            java.util.Arrays.asList(6, 5, 7),
            java.util.Arrays.asList(4, 1, 8, 3));
        check(minimumTotal(t) == 11, "case1");
        check(minimumTotal(java.util.Arrays.asList(java.util.Arrays.asList(-10))) == -10, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
