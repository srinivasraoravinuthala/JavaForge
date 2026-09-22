package pkg5leetcode.blind75;

/*
 * Contains Duplicate | LC 217
 * APPROACH: HashSet detects repeated values.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class blind75_LC217ContainsDuplicate {
    static boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int x : nums) if (!seen.add(x)) return true;
        return false;
    }

    public static void main(String[] args) {
        check(containsDuplicate(new int[]{1, 2, 3, 1}), "case1");
        check(!containsDuplicate(new int[]{1, 2, 3, 4}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
