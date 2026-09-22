package pkg5leetcode.blind75;

/*
 * Best Time to Buy and Sell Stock | LC 121
 * APPROACH: Track min price seen; maximize profit at each day.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC121BestTimeToBuyAndSellStock {
    static int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, best = 0;
        for (int p : prices) {
            min = Math.min(min, p);
            best = Math.max(best, p - min);
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxProfit(new int[]{7, 1, 5, 3, 6, 4}) == 5, "case1");
        check(maxProfit(new int[]{7, 6, 4, 3, 1}) == 0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
