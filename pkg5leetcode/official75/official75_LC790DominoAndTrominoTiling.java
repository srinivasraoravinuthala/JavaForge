package pkg5leetcode.official75;

/*
 * Domino and Tromino Tiling | LC 790
 * APPROACH: DP states full/partial row coverage mod 1e9+7.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC790DominoAndTrominoTiling {
    static int numTilings(int n) {
        final int MOD = 1_000_000_007;
        if (n < 3) return n;
        long[] dp = new long[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;
        for (int i = 4; i <= n; i++)
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % MOD;
        return (int) dp[n];
    }

    public static void main(String[] args) {
        check(numTilings(3) == 5, "case1");
        check(numTilings(4) == 11, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
