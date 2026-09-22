package pkg5leetcode.official75;

/*
 * Product of Array Except Self | LC 238
 * APPROACH: Prefix then suffix pass without division.
 * COMPLEXITY: Time O(n), Space O(1) extra
 */
public class official75_LC238ProductOfArrayExceptSelf {
    static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) res[i] = res[i - 1] * nums[i - 1];
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= suffix;
            suffix *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(productExceptSelf(new int[]{1,2,3,4}), new int[]{24,12,8,6}), "case1");
        check(java.util.Arrays.equals(productExceptSelf(new int[]{-1,1,0,-3,3}), new int[]{0,0,9,0,0}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
