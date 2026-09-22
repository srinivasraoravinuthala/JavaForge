package pkg5leetcode.interview150;

/*
 * Move Zeroes | LC 283
 * APPROACH: Write non-zero values forward; zero-fill rest.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC283MoveZeroes {
    static void moveZeroes(int[] nums) {
        int k = 0;
        for (int x : nums) if (x != 0) nums[k++] = x;
        while (k < nums.length) nums[k++] = 0;
    }

    public static void main(String[] args) {
        int[] a = {0, 1, 0, 3, 12};
        moveZeroes(a);
        check(java.util.Arrays.equals(a, new int[]{1, 3, 12, 0, 0}), "case1");
        int[] b = {0};
        moveZeroes(b);
        check(java.util.Arrays.equals(b, new int[]{0}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
