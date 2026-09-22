package pkg5leetcode.top100;

/*
 * Find First and Last Position of Element | LC 34
 * APPROACH: Two binary searches for leftmost and rightmost target.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class top100_LC34FindFirstAndLastPositionOfElement {
    static int[] searchRange(int[] nums, int target) {
        return new int[]{leftBound(nums, target), rightBound(nums, target)};
    }

    static int leftBound(int[] a, int t) {
        int lo = 0, hi = a.length - 1, res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] >= t) hi = mid - 1;
            else lo = mid + 1;
            if (a[mid] == t) res = mid;
        }
        return res;
    }

    static int rightBound(int[] a, int t) {
        int lo = 0, hi = a.length - 1, res = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] <= t) lo = mid + 1;
            else hi = mid - 1;
            if (a[mid] == t) res = mid;
        }
        return res;
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8), new int[]{3, 4}), "case1");
        check(java.util.Arrays.equals(searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6), new int[]{-1, -1}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
