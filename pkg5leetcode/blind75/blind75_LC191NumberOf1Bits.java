package pkg5leetcode.blind75;

/*
 * Number of 1 Bits | LC 191
 * APPROACH: Clear lowest set bit with n &= n-1 per iteration.
 * COMPLEXITY: Time O(k) bits set, Space O(1)
 */
public class blind75_LC191NumberOf1Bits {
    static int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n &= n - 1;
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        check(hammingWeight(11) == 3, "case1");
        check(hammingWeight(128) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
