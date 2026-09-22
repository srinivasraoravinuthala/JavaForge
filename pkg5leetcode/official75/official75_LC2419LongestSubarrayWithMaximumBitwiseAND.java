package pkg5leetcode.official75;

/*
 * Longest Subarray With Maximum Bitwise AND | LC 2419
 * APPROACH: Track current max AND and streak of elements >= max.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC2419LongestSubarrayWithMaximumBitwiseAND {
    static int longestSubarray(int[] nums) {
        int max = 0;
        for (int n : nums) max = Math.max(max, n);
        int best = 0, cur = 0;
        for (int n : nums) {
            if (n == max) { cur++; best = Math.max(best, cur); }
            else cur = 0;
        }
        return best;
    }

    public static void main(String[] args) {
        check(longestSubarray(new int[]{1,2,3,3,3,2,2}) == 3, "case1");
        check(longestSubarray(new int[]{1,2,3,4}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
