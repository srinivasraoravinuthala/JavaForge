package pkg5leetcode.blind75;

/*
 * House Robber II | LC 213
 * APPROACH: Max of linear rob on [0..n-2] and [1..n-1].
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC213HouseRobberII {
    static int rob(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(robLinear(nums, 0, nums.length - 2), robLinear(nums, 1, nums.length - 1));
    }

    static int robLinear(int[] nums, int lo, int hi) {
        int prev2 = 0, prev1 = 0;
        for (int i = lo; i <= hi; i++) {
            int cur = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        check(rob(new int[]{2, 3, 2}) == 3, "case1");
        check(rob(new int[]{1, 2, 3, 1}) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
