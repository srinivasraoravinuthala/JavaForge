package pkg5leetcode;

/*
 * LeetCode 70: Climbing Stairs  (Easy)
 * ------------------------------------
 * You can climb 1 or 2 steps at a time. How many distinct ways to reach step n?
 *
 * INSIGHT: ways(n) = ways(n-1) + ways(n-2) -> it's Fibonacci.
 * COMPLEXITY: Time O(n), Space O(1).
 */
public class leetcode10ClimbingStairs {

    static int climbStairs(int n) {
        if (n <= 2) return n;
        int a = 1, b = 2;                 // ways to reach step 1 and step 2
        for (int i = 3; i <= n; i++) { int c = a + b; a = b; b = c; }
        return b;
    }

    public static void main(String[] args) {
        check(climbStairs(1) == 1, "n=1");
        check(climbStairs(2) == 2, "n=2");
        check(climbStairs(3) == 3, "n=3");
        check(climbStairs(5) == 8, "n=5");
        check(climbStairs(10) == 89, "n=10");
        System.out.println("leetcode10ClimbingStairs: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
