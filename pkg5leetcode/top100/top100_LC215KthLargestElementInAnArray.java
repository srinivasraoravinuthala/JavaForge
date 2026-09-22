package pkg5leetcode.top100;

/*
 * Kth Largest Element in an Array | LC 215
 * APPROACH: Quickselect partition around pivot target index.
 * COMPLEXITY: Time O(n) average, Space O(1)
 */
public class top100_LC215KthLargestElementInAnArray {
    static int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int lo = 0, hi = nums.length - 1;
        while (true) {
            int p = partition(nums, lo, hi);
            if (p == target) return nums[p];
            if (p < target) lo = p + 1;
            else hi = p - 1;
        }
    }

    static int partition(int[] a, int lo, int hi) {
        int pivot = a[hi], i = lo;
        for (int j = lo; j < hi; j++) {
            if (a[j] <= pivot) swap(a, i++, j);
        }
        swap(a, i, hi);
        return i;
    }

    static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    public static void main(String[] args) {
        check(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2) == 5, "case1");
        check(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
