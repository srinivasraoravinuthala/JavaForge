package pkg5leetcode.blind75;

/*
 * Sum of Two Integers | LC 371
 * APPROACH: Bit manipulation XOR for sum, AND<<1 for carry until no carry.
 * COMPLEXITY: Time O(1), Space O(1)
 */
public class blind75_LC371SumOfTwoIntegers {
    static int getSum(int a, int b) {
        while (b != 0) {
            int carry = (a & b) << 1;
            a = a ^ b;
            b = carry;
        }
        return a;
    }

    public static void main(String[] args) {
        check(getSum(1, 2) == 3, "case1");
        check(getSum(2, 3) == 5, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
