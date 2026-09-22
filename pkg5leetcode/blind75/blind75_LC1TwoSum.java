package pkg5leetcode.blind75;

/*
 * Two Sum | LC 1
 * APPROACH: One-pass hash map stores value->index; check complement each step.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class blind75_LC1TwoSum {
    static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>();
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
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
