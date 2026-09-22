package pkg5leetcode.blind75;

/*
 * Coin Change | LC 322
 * APPROACH: Bottom-up DP min coins for each amount 1..amount.
 * COMPLEXITY: Time O(amount * coins), Space O(amount)
 */
import java.util.*;

public class blind75_LC322CoinChange {
    static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int a = 1; a <= amount; a++)
            for (int c : coins)
                if (c <= a) dp[a] = Math.min(dp[a], dp[a - c] + 1);
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        check(coinChange(new int[]{1, 2, 5}, 11) == 3, "case1");
        check(coinChange(new int[]{2}, 3) == -1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
