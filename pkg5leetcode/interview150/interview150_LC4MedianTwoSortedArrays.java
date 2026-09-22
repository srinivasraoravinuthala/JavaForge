package pkg5leetcode.interview150;

/*
 * Median of Two Sorted Arrays | LC 4
 * APPROACH: Binary search smaller array partition; O(log min(n,m)).
 * COMPLEXITY: Time O(log(min(n,m))), Space O(1)
 */
public class interview150_LC4MedianTwoSortedArrays {
    static double findMedianSortedArrays(int[] a, int[] b) {
        if (a.length > b.length) return findMedianSortedArrays(b, a);
        int n = a.length, m = b.length, lo = 0, hi = n;
        while (lo <= hi) {
            int i = (lo + hi) / 2, j = (n + m + 1) / 2 - i;
            int aLo = i == 0 ? Integer.MIN_VALUE : a[i - 1];
            int aHi = i == n ? Integer.MAX_VALUE : a[i];
            int bLo = j == 0 ? Integer.MIN_VALUE : b[j - 1];
            int bHi = j == m ? Integer.MAX_VALUE : b[j];
            if (aLo <= bHi && bLo <= aHi) {
                if ((n + m) % 2 == 0) return (Math.max(aLo, bLo) + Math.min(aHi, bHi)) / 2.0;
                return Math.max(aLo, bLo);
            }
            if (aLo > bHi) hi = i - 1;
            else lo = i + 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        check(findMedianSortedArrays(new int[]{1, 3}, new int[]{2}) == 2.0, "case1");
        check(findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}) == 2.5, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
