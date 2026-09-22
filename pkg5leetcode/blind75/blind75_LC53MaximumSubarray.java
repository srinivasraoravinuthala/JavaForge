package pkg5leetcode.blind75;

/*
 * Maximum Subarray | LC 53
 * APPROACH: Kadane's algorithm tracks best ending-here sum.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC53MaximumSubarray {
    static int maxSubArray(int[] nums) {
        int best = nums[0], cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);
            best = Math.max(best, cur);
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6, "case1");
        check(maxSubArray(new int[]{1}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
