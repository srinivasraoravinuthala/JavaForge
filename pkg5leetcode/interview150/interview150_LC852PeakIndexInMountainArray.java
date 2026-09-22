package pkg5leetcode.interview150;

/*
 * Peak Index in a Mountain Array | LC 852
 * APPROACH: Binary search where mid slope points uphill.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class interview150_LC852PeakIndexInMountainArray {
    static int peakIndexInMountainArray(int[] arr) {
        int lo = 0, hi = arr.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (arr[mid] < arr[mid + 1]) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        check(peakIndexInMountainArray(new int[]{0, 1, 0}) == 1, "case1");
        check(peakIndexInMountainArray(new int[]{0, 2, 1, 0}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
