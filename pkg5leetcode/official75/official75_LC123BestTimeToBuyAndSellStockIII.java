package pkg5leetcode.official75;

/*
 * Best Time to Buy and Sell Stock III | LC 123
 * APPROACH: DP track profit after 0/1/2 transactions.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC123BestTimeToBuyAndSellStockIII {
    static int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE / 2, sell1 = 0;
        int buy2 = Integer.MIN_VALUE / 2, sell2 = 0;
        for (int p : prices) {
            buy1 = Math.max(buy1, -p);
            sell1 = Math.max(sell1, buy1 + p);
            buy2 = Math.max(buy2, sell1 - p);
            sell2 = Math.max(sell2, buy2 + p);
        }
        return sell2;
    }

    public static void main(String[] args) {
        check(maxProfit(new int[]{3,3,5,0,0,3,1,4}) == 6, "case1");
        check(maxProfit(new int[]{1,2,3,4,5}) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
