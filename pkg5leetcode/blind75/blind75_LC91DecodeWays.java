package pkg5leetcode.blind75;

/*
 * Decode Ways | LC 91
 * APPROACH: DP count ways to decode prefix; handle '0' invalid splits.
 * COMPLEXITY: Time O(n), Space O(n)
 */
public class blind75_LC91DecodeWays {
    static int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (s.charAt(i - 1) != '0') dp[i] += dp[i - 1];
            int two = Integer.parseInt(s.substring(i - 2, i));
            if (two >= 10 && two <= 26) dp[i] += dp[i - 2];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        check(numDecodings("12") == 2, "case1");
        check(numDecodings("226") == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
