package pkg5leetcode.blind75;

/*
 * Search in Rotated Sorted Array | LC 33
 * APPROACH: Binary search identifying sorted half.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class blind75_LC33SearchInRotatedSortedArray {
    static int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid]) hi = mid - 1;
                else lo = mid + 1;
            } else {
                if (nums[mid] < target && target <= nums[hi]) lo = mid + 1;
                else hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        check(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0) == 4, "case1");
        check(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3) == -1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
