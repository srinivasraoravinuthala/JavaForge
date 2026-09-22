package pkg5leetcode.interview150;

/*
 * Fibonacci Number | LC 509
 * APPROACH: Iterative DP with two previous values.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC509FibonacciNumber {
    static int fib(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        for (int i = 2; i <= n; i++) {
            int c = a + b;
            a = b;
            b = c;
        }
        return b;
    }

    public static void main(String[] args) {
        check(fib(2) == 1, "case1");
        check(fib(3) == 2, "case2");
        check(fib(4) == 3, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
