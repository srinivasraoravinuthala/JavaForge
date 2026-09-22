package pkg5leetcode.blind75;

/*
 * Container With Most Water | LC 11
 * APPROACH: Two pointers move shorter line inward.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC11ContainerWithMostWater {
    static int maxArea(int[] h) {
        int lo = 0, hi = h.length - 1, best = 0;
        while (lo < hi) {
            best = Math.max(best, Math.min(h[lo], h[hi]) * (hi - lo));
            if (h[lo] < h[hi]) lo++; else hi--;
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}) == 49, "case1");
        check(maxArea(new int[]{1, 1}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
