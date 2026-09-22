package pkg5leetcode.blind75;

/*
 * Reverse Bits | LC 190
 * APPROACH: Extract LSB, build result left-to-right over 32 iterations.
 * COMPLEXITY: Time O(32), Space O(1)
 */
public class blind75_LC190ReverseBits {
    static int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        check(reverseBits(43261596) == 964176192, "case1");
        check(reverseBits(0) == 0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
