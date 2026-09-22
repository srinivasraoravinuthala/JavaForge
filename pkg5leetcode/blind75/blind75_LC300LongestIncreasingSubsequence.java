package pkg5leetcode.blind75;

/*
 * Longest Increasing Subsequence | LC 300
 * APPROACH: Patience sorting with binary search on tails array.
 * COMPLEXITY: Time O(n log n), Space O(n)
 */
import java.util.*;

public class blind75_LC300LongestIncreasingSubsequence {
    static int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int lo = 0, hi = size;
            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;
                if (tails[mid] < x) lo = mid + 1;
                else hi = mid;
            }
            tails[lo] = x;
            if (lo == size) size++;
        }
        return size;
    }

    public static void main(String[] args) {
        check(lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}) == 4, "case1");
        check(lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3}) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
