package pkg5leetcode.interview150;

/*
 * Happy Number | LC 202
 * APPROACH: Floyd cycle detection on sum-of-squares sequence.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class interview150_LC202HappyNumber {
    static int next(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }

    static boolean isHappy(int n) {
        int slow = n, fast = next(n);
        while (fast != 1 && slow != fast) {
            slow = next(slow);
            fast = next(next(fast));
        }
        return fast == 1;
    }

    public static void main(String[] args) {
        check(isHappy(19), "case1");
        check(!isHappy(2), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
