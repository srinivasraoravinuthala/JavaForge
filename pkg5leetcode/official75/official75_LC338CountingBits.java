package pkg5leetcode.official75;

/*
 * Counting Bits | LC 338
 * APPROACH: dp[i] = dp[i>>1] + (i&1).
 * COMPLEXITY: Time O(n), Space O(n)
 */
public class official75_LC338CountingBits {
    static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) dp[i] = dp[i >> 1] + (i & 1);
        return dp;
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(countBits(2), new int[]{0,1,1}), "case1");
        check(java.util.Arrays.equals(countBits(5), new int[]{0,1,1,2,1,2}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
