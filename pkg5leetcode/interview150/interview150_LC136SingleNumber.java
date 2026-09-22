package pkg5leetcode.interview150;

/*
 * Single Number | LC 136
 * APPROACH: XOR all numbers; duplicates cancel to zero.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC136SingleNumber {
    static int singleNumber(int[] nums) {
        int x = 0;
        for (int n : nums) x ^= n;
        return x;
    }

    public static void main(String[] args) {
        check(singleNumber(new int[]{2, 2, 1}) == 1, "case1");
        check(singleNumber(new int[]{4, 1, 2, 1, 2}) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
