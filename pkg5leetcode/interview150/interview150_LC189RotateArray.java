package pkg5leetcode.interview150;

/*
 * Rotate Array | LC 189
 * APPROACH: Reverse whole array then reverse first k and rest.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC189RotateArray {
    static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    static void reverse(int[] a, int lo, int hi) {
        while (lo < hi) { int t = a[lo]; a[lo++] = a[hi]; a[hi--] = t; }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        rotate(a, 3);
        check(java.util.Arrays.equals(a, new int[]{5, 6, 7, 1, 2, 3, 4}), "case1");
        int[] b = {-1};
        rotate(b, 2);
        check(java.util.Arrays.equals(b, new int[]{-1}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
