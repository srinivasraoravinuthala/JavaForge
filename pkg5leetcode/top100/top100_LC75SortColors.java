package pkg5leetcode.top100;

/*
 * Sort Colors | LC 75
 * APPROACH: Dutch national flag three pointers for 0,1,2 regions.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class top100_LC75SortColors {
    static void sortColors(int[] nums) {
        int lo = 0, hi = nums.length - 1, i = 0;
        while (i <= hi) {
            if (nums[i] == 0) swap(nums, lo++, i++);
            else if (nums[i] == 2) swap(nums, i, hi--);
            else i++;
        }
    }

    static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    public static void main(String[] args) {
        int[] a = {2, 0, 2, 1, 1, 0};
        sortColors(a);
        check(java.util.Arrays.equals(a, new int[]{0, 0, 1, 1, 2, 2}), "case1");
        int[] b = {2, 0, 1};
        sortColors(b);
        check(java.util.Arrays.equals(b, new int[]{0, 1, 2}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
