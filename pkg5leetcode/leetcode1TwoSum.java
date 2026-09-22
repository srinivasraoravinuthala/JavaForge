package pkg5leetcode;

/*
 * LeetCode 1: Two Sum  (Easy)
 * ---------------------------
 * Given an array and a target, return indices of the two numbers that add up to target.
 *
 * APPROACH: one-pass hash map. For each x, check if (target - x) was seen.
 * COMPLEXITY: Time O(n), Space O(n).  Brute force is O(n^2).
 */
import java.util.*;

public class leetcode1TwoSum {

    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();   // value -> index
        for (int i = 0; i < nums.length; i++) {
            int need = target - nums[i];
            if (seen.containsKey(need)) return new int[]{seen.get(need), i};
            seen.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        check(Arrays.equals(twoSum(new int[]{2, 7, 11, 15}, 9), new int[]{0, 1}), "case1");
        check(Arrays.equals(twoSum(new int[]{3, 2, 4}, 6), new int[]{1, 2}), "case2");
        check(Arrays.equals(twoSum(new int[]{3, 3}, 6), new int[]{0, 1}), "case3");
        System.out.println("leetcode1TwoSum: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
