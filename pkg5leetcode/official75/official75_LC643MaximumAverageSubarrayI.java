package pkg5leetcode.official75;

/*
 * Maximum Average Subarray I | LC 643
 * APPROACH: Fixed-size sliding window sum of length k.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC643MaximumAverageSubarrayI {
    static double findMaxAverage(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) sum += nums[i];
        int best = sum;
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - nums[i - k];
            best = Math.max(best, sum);
        }
        return (double) best / k;
    }

    public static void main(String[] args) {
        check(Math.abs(findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4) - 12.75) < 1e-9, "case1");
        check(Math.abs(findMaxAverage(new int[]{5}, 1) - 5.0) < 1e-9, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
