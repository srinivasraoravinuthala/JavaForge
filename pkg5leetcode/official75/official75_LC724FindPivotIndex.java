package pkg5leetcode.official75;

/*
 * Find Pivot Index | LC 724
 * APPROACH: Prefix sum; pivot where left sum equals right sum.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC724FindPivotIndex {
    static int pivotIndex(int[] nums) {
        int total = 0;
        for (int n : nums) total += n;
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (left == total - left - nums[i]) return i;
            left += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        check(pivotIndex(new int[]{1,7,3,6,5,6}) == 3, "case1");
        check(pivotIndex(new int[]{1,2,3}) == -1, "case2");
        check(pivotIndex(new int[]{2,1,-1}) == 0, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
