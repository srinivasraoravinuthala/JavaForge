package pkg5leetcode.interview150;

/*
 * Remove Duplicates from Sorted Array | LC 26
 * APPROACH: Two pointers; write unique values at slow index.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC26RemoveDuplicates {
    static int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int k = 1;
        for (int i = 1; i < nums.length; i++)
            if (nums[i] != nums[k - 1]) nums[k++] = nums[i];
        return k;
    }

    public static void main(String[] args) {
        int[] a = {1, 1, 2};
        check(removeDuplicates(a) == 2 && a[0] == 1 && a[1] == 2, "case1");
        int[] b = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        check(removeDuplicates(b) == 5, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
