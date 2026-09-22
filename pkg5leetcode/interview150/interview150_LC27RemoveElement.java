package pkg5leetcode.interview150;

/*
 * Remove Element | LC 27
 * APPROACH: Two pointers skip values equal to val.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC27RemoveElement {
    static int removeElement(int[] nums, int val) {
        int k = 0;
        for (int x : nums) if (x != val) nums[k++] = x;
        return k;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 2, 3};
        check(removeElement(a, 3) == 2, "case1");
        int[] b = {0, 1, 2, 2, 3, 0, 4, 2};
        check(removeElement(b, 2) == 5, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
