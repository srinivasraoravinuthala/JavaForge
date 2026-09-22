package pkg5leetcode.official75;

/*
 * Guess Number Higher or Lower | LC 374
 * APPROACH: Binary search on answer range.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class official75_LC374GuessNumberHigherOrLower {
    static int pick = 6;

    static int guess(int num) {
        return Integer.compare(pick, num);
    }

    static int guessNumber(int n) {
        int lo = 1, hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int g = guess(mid);
            if (g == 0) return mid;
            if (g < 0) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo;
    }

    public static void main(String[] args) {
        pick = 6;
        check(guessNumber(10) == 6, "case1");
        pick = 1;
        check(guessNumber(1) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
