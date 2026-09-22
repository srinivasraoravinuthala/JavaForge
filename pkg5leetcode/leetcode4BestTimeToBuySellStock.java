package pkg5leetcode;

/*
 * LeetCode 121: Best Time to Buy and Sell Stock  (Easy)
 * -----------------------------------------------------
 * One buy + one sell. Maximize profit (sell after buy).
 *
 * APPROACH: track the minimum price so far; best profit = price - minSoFar.
 * COMPLEXITY: Time O(n), Space O(1).
 */
public class leetcode4BestTimeToBuySellStock {

    static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE, best = 0;
        for (int p : prices) {
            minPrice = Math.min(minPrice, p);
            best = Math.max(best, p - minPrice);
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxProfit(new int[]{7, 1, 5, 3, 6, 4}) == 5, "buy@1 sell@6");
        check(maxProfit(new int[]{7, 6, 4, 3, 1}) == 0, "decreasing -> 0");
        check(maxProfit(new int[]{1, 2}) == 1, "simple");
        System.out.println("leetcode4BestTimeToBuySellStock: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
