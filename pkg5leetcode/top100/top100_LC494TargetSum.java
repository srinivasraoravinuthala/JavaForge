package pkg5leetcode.top100;

/*
 * Target Sum | LC 494
 * APPROACH: DP count ways to reach each sum adding +/- each number.
 * COMPLEXITY: Time O(n*sum), Space O(sum)
 */
public class top100_LC494TargetSum {
    static int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int x : nums) sum += x;
        if ((target + sum) % 2 != 0 || target > sum) return 0;
        int need = (target + sum) / 2;
        int[] dp = new int[need + 1];
        dp[0] = 1;
        for (int x : nums) {
            for (int s = need; s >= x; s--) dp[s] += dp[s - x];
        }
        return dp[need];
    }

    public static void main(String[] args) {
        check(findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3) == 5, "case1");
        check(findTargetSumWays(new int[]{1}, 1) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
