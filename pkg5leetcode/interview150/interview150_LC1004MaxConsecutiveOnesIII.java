package pkg5leetcode.interview150;

/*
 * Max Consecutive Ones III | LC 1004
 * APPROACH: Sliding window max length with at most k zeros flipped.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC1004MaxConsecutiveOnesIII {
    static int longestOnes(int[] nums, int k) {
        int lo = 0, zeros = 0, best = 0;
        for (int hi = 0; hi < nums.length; hi++) {
            if (nums[hi] == 0) zeros++;
            while (zeros > k) if (nums[lo++] == 0) zeros--;
            best = Math.max(best, hi - lo + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        check(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2) == 6, "case1");
        check(longestOnes(new int[]{0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, 3) == 10, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
