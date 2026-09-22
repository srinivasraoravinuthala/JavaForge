package pkg5leetcode.interview150;

/*
 * Koko Eating Bananas | LC 875
 * APPROACH: Binary search minimum eating speed; check hours needed.
 * COMPLEXITY: Time O(n log max), Space O(1)
 */
public class interview150_LC875KokoEatingBananas {
    static int minEatingSpeed(int[] piles, int h) {
        int lo = 1, hi = 0;
        for (int p : piles) hi = Math.max(hi, p);
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (hours(piles, mid) <= h) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

    static long hours(int[] piles, int speed) {
        long h = 0;
        for (int p : piles) h += (p + speed - 1) / speed;
        return h;
    }

    public static void main(String[] args) {
        check(minEatingSpeed(new int[]{3, 6, 7, 11}, 8) == 4, "case1");
        check(minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5) == 30, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
