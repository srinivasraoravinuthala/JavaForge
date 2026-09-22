package pkg5leetcode.official75;

/*
 * Max Consecutive Ones III | LC 1004
 * APPROACH: Sliding window shrink when zeros exceed k.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC1004MaxConsecutiveOnesIII {
    static int longestOnes(int[] nums, int k) {
        int l = 0, zeros = 0, best = 0;
        for (int r = 0; r < nums.length; r++) {
            if (nums[r] == 0) zeros++;
            while (zeros > k) {
                if (nums[l] == 0) zeros--;
                l++;
            }
            best = Math.max(best, r - l + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        check(longestOnes(new int[]{1,1,1,0,0,0,1,1,1,1,0}, 2) == 6, "case1");
        check(longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1,0}, 3) == 10, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
