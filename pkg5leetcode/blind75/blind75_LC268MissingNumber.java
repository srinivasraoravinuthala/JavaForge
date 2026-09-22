package pkg5leetcode.blind75;

/*
 * Missing Number | LC 268
 * APPROACH: XOR all indices and values; pairs cancel leaving missing.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC268MissingNumber {
    static int missingNumber(int[] nums) {
        int x = nums.length;
        for (int i = 0; i < nums.length; i++) x ^= i ^ nums[i];
        return x;
    }

    public static void main(String[] args) {
        check(missingNumber(new int[]{3, 0, 1}) == 2, "case1");
        check(missingNumber(new int[]{0, 1}) == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
