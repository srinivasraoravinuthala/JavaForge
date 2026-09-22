package pkg5leetcode.top100;

/*
 * Unique Binary Search Trees | LC 96
 * APPROACH: Catalan DP: ways(n) = sum ways(i)*ways(n-1-i).
 * COMPLEXITY: Time O(n^2), Space O(n)
 */
public class top100_LC96UniqueBinarySearchTrees {
    static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int nodes = 1; nodes <= n; nodes++) {
            for (int root = 1; root <= nodes; root++)
                dp[nodes] += dp[root - 1] * dp[nodes - root];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        check(numTrees(3) == 5, "case1");
        check(numTrees(1) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
