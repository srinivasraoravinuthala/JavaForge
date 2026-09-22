package pkg5leetcode;

/*
 * LeetCode 238: Product of Array Except Self  (Medium)
 * ----------------------------------------------------
 * Return an array where output[i] = product of all elements except nums[i],
 * WITHOUT using division and in O(n).
 *
 * APPROACH: prefix products (left pass) then multiply by suffix products (right pass).
 * COMPLEXITY: Time O(n), Space O(1) extra (output array aside).
 */
import java.util.*;

public class leetcode6ProductExceptSelf {

    static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) res[i] = res[i - 1] * nums[i - 1];   // prefix
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {                               // suffix
            res[i] *= suffix;
            suffix *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        check(Arrays.equals(productExceptSelf(new int[]{1, 2, 3, 4}), new int[]{24, 12, 8, 6}), "basic");
        check(Arrays.equals(productExceptSelf(new int[]{-1, 1, 0, -3, 3}), new int[]{0, 0, 9, 0, 0}), "with zero");
        System.out.println("leetcode6ProductExceptSelf: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
