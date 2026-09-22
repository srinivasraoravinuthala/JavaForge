package pkg5leetcode.official75;

/*
 * Best Time to Buy and Sell Stock IV | LC 188
 * APPROACH: DP buy[k] and sell[k] states per day.
 * COMPLEXITY: Time O(nk), Space O(k)
 */
public class official75_LC188BestTimeToBuyAndSellStockIV {
    static int maxProfit(int k, int[] prices) {
        if (k >= prices.length / 2) {
            int profit = 0;
            for (int i = 1; i < prices.length; i++)
                if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            return profit;
        }
        int[] buy = new int[k + 1], sell = new int[k + 1];
        java.util.Arrays.fill(buy, Integer.MIN_VALUE / 2);
        for (int p : prices)
            for (int t = 1; t <= k; t++) {
                buy[t] = Math.max(buy[t], sell[t - 1] - p);
                sell[t] = Math.max(sell[t], buy[t] + p);
            }
        return sell[k];
    }

    public static void main(String[] args) {
        check(maxProfit(2, new int[]{2,4,1}) == 2, "case1");
        check(maxProfit(2, new int[]{3,2,6,5,0,3}) == 7, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
