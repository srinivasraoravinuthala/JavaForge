package pkg5leetcode.blind75;

/*
 * Find Minimum in Rotated Sorted Array | LC 153
 * APPROACH: Binary search on unsorted half.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class blind75_LC153FindMinimumInRotatedSortedArray {
    static int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) lo = mid + 1;
            else hi = mid;
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        check(findMin(new int[]{3, 4, 5, 1, 2}) == 1, "case1");
        check(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}) == 0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
