package pkg5leetcode.interview150;

/*
 * Subarray Sum Equals K | LC 560
 * APPROACH: Prefix sum hash map counts subarrays with needed prefix.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class interview150_LC560SubarraySumEqualsK {
    static int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        int sum = 0, res = 0;
        for (int x : nums) {
            sum += x;
            res += cnt.getOrDefault(sum - k, 0);
            cnt.merge(sum, 1, Integer::sum);
        }
        return res;
    }

    public static void main(String[] args) {
        check(subarraySum(new int[]{1, 1, 1}, 2) == 2, "case1");
        check(subarraySum(new int[]{1, 2, 3}, 3) == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
