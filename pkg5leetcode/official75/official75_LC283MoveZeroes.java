package pkg5leetcode.official75;

/*
 * Move Zeroes | LC 283
 * APPROACH: Write non-zeroes left; fill rest with zero.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC283MoveZeroes {
    static void moveZeroes(int[] nums) {
        int w = 0;
        for (int n : nums) if (n != 0) nums[w++] = n;
        while (w < nums.length) nums[w++] = 0;
    }

    public static void main(String[] args) {
        int[] a = {0,1,0,3,12};
        moveZeroes(a);
        check(java.util.Arrays.equals(a, new int[]{1,3,12,0,0}), "case1");
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
