package pkg5leetcode.blind75;

/*
 * Climbing Stairs | LC 70
 * APPROACH: Fibonacci DP; ways(n) = ways(n-1) + ways(n-2).
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC70ClimbingStairs {
    static int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2;
        for (int i = 3; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        check(climbStairs(2) == 2, "case1");
        check(climbStairs(3) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
