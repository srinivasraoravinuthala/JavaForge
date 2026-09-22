package pkg5leetcode.official75;

/*
 * Find Peak Element | LC 162
 * APPROACH: Binary search on slope compare mid and mid+1.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class official75_LC162FindPeakElement {
    static int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] < nums[mid + 1]) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        check(findPeakElement(new int[]{1,2,3,1}) == 2, "case1");
        check(findPeakElement(new int[]{1,2,1,3,5,6,4}) == 5, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
