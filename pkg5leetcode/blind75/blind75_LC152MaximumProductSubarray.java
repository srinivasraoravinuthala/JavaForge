package pkg5leetcode.blind75;

/*
 * Maximum Product Subarray | LC 152
 * APPROACH: Track max and min product ending at each index (negatives flip).
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC152MaximumProductSubarray {
    static int maxProduct(int[] nums) {
        int best = nums[0], max = nums[0], min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) { int t = max; max = min; min = t; }
            max = Math.max(nums[i], max * nums[i]);
            min = Math.min(nums[i], min * nums[i]);
            best = Math.max(best, max);
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxProduct(new int[]{2, 3, -2, 4}) == 6, "case1");
        check(maxProduct(new int[]{-2, 0, -1}) == 0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
