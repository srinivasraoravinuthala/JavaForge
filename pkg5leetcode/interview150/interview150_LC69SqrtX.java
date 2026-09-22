package pkg5leetcode.interview150;

/*
 * Sqrt(x) | LC 69
 * APPROACH: Binary search on answer in [0, x].
 * COMPLEXITY: Time O(log x), Space O(1)
 */
public class interview150_LC69SqrtX {
    static int mySqrt(int x) {
        if (x < 2) return x;
        int lo = 1, hi = x / 2;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            long sq = (long) mid * mid;
            if (sq == x) return mid;
            if (sq < x) lo = mid + 1;
            else hi = mid - 1;
        }
        return hi;
    }

    public static void main(String[] args) {
        check(mySqrt(4) == 2, "case1");
        check(mySqrt(8) == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
