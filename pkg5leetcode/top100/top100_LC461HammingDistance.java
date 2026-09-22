package pkg5leetcode.top100;

/*
 * Hamming Distance | LC 461
 * APPROACH: XOR then count set bits in result.
 * COMPLEXITY: Time O(1), Space O(1)
 */
public class top100_LC461HammingDistance {
    static int hammingDistance(int x, int y) {
        int diff = x ^ y, count = 0;
        while (diff != 0) {
            count += diff & 1;
            diff >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        check(hammingDistance(1, 4) == 2, "case1");
        check(hammingDistance(3, 1) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
