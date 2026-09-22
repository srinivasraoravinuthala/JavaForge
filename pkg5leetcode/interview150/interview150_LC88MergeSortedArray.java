package pkg5leetcode.interview150;

/*
 * Merge Sorted Array | LC 88
 * APPROACH: Fill from end comparing largest elements of both arrays.
 * COMPLEXITY: Time O(m+n), Space O(1)
 */
public class interview150_LC88MergeSortedArray {
    static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;
        while (j >= 0) {
            if (i >= 0 && nums1[i] > nums2[j]) nums1[k--] = nums1[i--];
            else nums1[k--] = nums2[j--];
        }
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 0, 0, 0};
        merge(a, 3, new int[]{2, 5, 6}, 3);
        check(java.util.Arrays.equals(a, new int[]{1, 2, 2, 3, 5, 6}), "case1");
        int[] b = {1};
        merge(b, 1, new int[]{}, 0);
        check(java.util.Arrays.equals(b, new int[]{1}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
