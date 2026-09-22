package pkg5leetcode.interview150;

/*
 * Contains Duplicate II | LC 219
 * APPROACH: Hash map stores last index; check distance <= k.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class interview150_LC219ContainsDuplicateII {
    static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> idx = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (idx.containsKey(nums[i]) && i - idx.get(nums[i]) <= k) return true;
            idx.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        check(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3), "case1");
        check(!containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
