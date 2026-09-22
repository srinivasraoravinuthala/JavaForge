package pkg5leetcode.interview150;

/*
 * Squares of a Sorted Array | LC 977
 * APPROACH: Fill result from ends comparing absolute values.
 * COMPLEXITY: Time O(n), Space O(n)
 */
public class interview150_LC977SquaresOfASortedArray {
    static int[] sortedSquares(int[] nums) {
        int n = nums.length, lo = 0, hi = n - 1;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            if (Math.abs(nums[lo]) > Math.abs(nums[hi])) {
                res[i] = nums[lo] * nums[lo];
                lo++;
            } else {
                res[i] = nums[hi] * nums[hi];
                hi--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(sortedSquares(new int[]{-4, -1, 0, 3, 10}), new int[]{0, 1, 9, 16, 100}), "case1");
        check(java.util.Arrays.equals(sortedSquares(new int[]{-7, -3, 2, 3, 11}), new int[]{4, 9, 9, 49, 121}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
