package pkg5leetcode.interview150;

/*
 * Search Insert Position | LC 35
 * APPROACH: Binary search for first index where nums[i] >= target.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class interview150_LC35SearchInsertPosition {
    static int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] < target) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        check(searchInsert(new int[]{1, 3, 5, 6}, 5) == 2, "case1");
        check(searchInsert(new int[]{1, 3, 5, 6}, 2) == 1, "case2");
        check(searchInsert(new int[]{1, 3, 5, 6}, 7) == 4, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
