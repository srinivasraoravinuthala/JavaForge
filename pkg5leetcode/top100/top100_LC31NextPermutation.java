package pkg5leetcode.top100;

/*
 * Next Permutation | LC 31
 * APPROACH: Find pivot, swap with rightmost larger, reverse suffix.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class top100_LC31NextPermutation {
    static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }

    static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    static void reverse(int[] a, int lo, int hi) {
        while (lo < hi) swap(a, lo++, hi--);
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3};
        nextPermutation(a);
        check(java.util.Arrays.equals(a, new int[]{1, 3, 2}), "case1");
        int[] b = {3, 2, 1};
        nextPermutation(b);
        check(java.util.Arrays.equals(b, new int[]{1, 2, 3}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
