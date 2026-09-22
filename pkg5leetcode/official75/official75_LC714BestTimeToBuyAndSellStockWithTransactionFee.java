package pkg5leetcode.official75;

/*
 * Best Time to Buy and Sell Stock with Transaction Fee | LC 714
 * APPROACH: DP cash vs holding stock each day.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC714BestTimeToBuyAndSellStockWithTransactionFee {
    static int maxProfit(int[] prices, int fee) {
        int cash = 0, hold = Integer.MIN_VALUE / 2;
        for (int p : prices) {
            int prev = cash;
            cash = Math.max(cash, hold + p - fee);
            hold = Math.max(hold, prev - p);
        }
        return cash;
    }

    public static void main(String[] args) {
        check(maxProfit(new int[]{1,3,2,8,4,9}, 2) == 8, "case1");
        check(maxProfit(new int[]{2,1,4,5,2,9,7}, 3) == 5, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
