package pkg5leetcode.top100;

/*
 * Subsets | LC 78
 * APPROACH: Backtrack include/exclude each element.
 * COMPLEXITY: Time O(2^n), Space O(n)
 */
import java.util.*;

public class top100_LC78Subsets {
    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    static void backtrack(int[] nums, int i, List<Integer> cur, List<List<Integer>> res) {
        res.add(new ArrayList<>(cur));
        for (int j = i; j < nums.length; j++) {
            cur.add(nums[j]);
            backtrack(nums, j + 1, cur, res);
            cur.remove(cur.size() - 1);
        }
    }

    public static void main(String[] args) {
        check(subsets(new int[]{1, 2, 3}).size() == 8, "case1");
        check(subsets(new int[]{0}).size() == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
