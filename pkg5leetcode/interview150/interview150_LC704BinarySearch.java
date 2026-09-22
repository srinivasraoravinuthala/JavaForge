package pkg5leetcode.interview150;

/*
 * Binary Search | LC 704
 * APPROACH: Classic binary search on sorted array.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class interview150_LC704BinarySearch {
    static int search(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            if (nums[mid] < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        check(search(new int[]{-1, 0, 3, 5, 9, 12}, 9) == 4, "case1");
        check(search(new int[]{-1, 0, 3, 5, 9, 12}, 2) == -1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
