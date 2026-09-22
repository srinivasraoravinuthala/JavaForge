package pkg5leetcode.official75;

/*
 * Longest Subarray of 1's After Deleting One Element | LC 1493
 * APPROACH: Sliding window allow at most one zero.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC1493LongestSubarrayOfOnesAfterDeletingOneElement {
    static int longestSubarray(int[] nums) {
        int l = 0, zeros = 0, best = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) zeros++;
            while (zeros > 1) {
                if (nums[l] == 0) zeros--;
                l++;
            }
            best = Math.max(best, r - l);
        }
        return best;
    }

    public static void main(String[] args) {
        check(longestSubarray(new int[]{1,1,0,1}) == 3, "case1");
        check(longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}) == 5, "case2");
        check(longestSubarray(new int[]{1,1,1}) == 2, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
