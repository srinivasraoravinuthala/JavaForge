package pkg5leetcode.interview150;

/*
 * Plus One | LC 66
 * APPROACH: Add from end with carry; prepend 1 if overflow.
 * COMPLEXITY: Time O(n), Space O(1) excluding output
 */
public class interview150_LC66PlusOne {
    static int[] plusOne(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) { digits[i]++; return digits; }
            digits[i] = 0;
        }
        int[] res = new int[digits.length + 1];
        res[0] = 1;
        return res;
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(plusOne(new int[]{1, 2, 3}), new int[]{1, 2, 4}), "case1");
        check(java.util.Arrays.equals(plusOne(new int[]{9}), new int[]{1, 0}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
