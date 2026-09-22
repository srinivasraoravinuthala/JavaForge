package pkg5leetcode;

/*
 * LeetCode 53: Maximum Subarray  (Medium)
 * ---------------------------------------
 * Find the contiguous subarray with the largest sum.
 *
 * APPROACH: Kadane's algorithm. Track best sum ending here; reset when it goes negative.
 * COMPLEXITY: Time O(n), Space O(1).
 */
public class leetcode5MaxSubArray {

    static int maxSubArray(int[] nums) {
        int best = nums[0], cur = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cur = Math.max(nums[i], cur + nums[i]);   // extend or restart
            best = Math.max(best, cur);
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}) == 6, "mixed");   // [4,-1,2,1]
        check(maxSubArray(new int[]{1}) == 1, "single");
        check(maxSubArray(new int[]{5, 4, -1, 7, 8}) == 23, "mostly positive");
        check(maxSubArray(new int[]{-1, -2, -3}) == -1, "all negative");
        System.out.println("leetcode5MaxSubArray: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
