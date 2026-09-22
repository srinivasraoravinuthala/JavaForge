package pkg5leetcode.official75;

/*
 * Min Cost Climbing Stairs | LC 746
 * APPROACH: DP min cost to reach each step.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC746MinCostClimbingStairs {
    static int minCostClimbingStairs(int[] cost) {
        int a = 0, b = 0;
        for (int i = 2; i <= cost.length; i++) {
            int c = Math.min(b + cost[i - 1], a + cost[i - 2]);
            a = b; b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        check(minCostClimbingStairs(new int[]{10,15,20}) == 15, "case1");
        check(minCostClimbingStairs(new int[]{1,100,1,1,1,100,1,1,100,1}) == 6, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
