package pkg5leetcode.blind75;

/*
 * House Robber | LC 198
 * APPROACH: DP rob current + skip prev vs skip current.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC198HouseRobber {
    static int rob(int[] nums) {
        int prev2 = 0, prev1 = 0;
        for (int x : nums) {
            int cur = Math.max(prev1, prev2 + x);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        check(rob(new int[]{1, 2, 3, 1}) == 4, "case1");
        check(rob(new int[]{2, 7, 9, 3, 1}) == 12, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
