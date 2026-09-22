package pkg5leetcode.blind75;

/*
 * Combination Sum | LC 39
 * APPROACH: Backtracking with reuse; sort and prune early.
 * COMPLEXITY: Time O(2^n) worst case, Space O(target)
 */
import java.util.*;

public class blind75_LC39CombinationSum {
    static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    static void backtrack(int[] c, int rem, int start, List<Integer> path, List<List<Integer>> res) {
        if (rem == 0) { res.add(new ArrayList<>(path)); return; }
        for (int i = start; i < c.length; i++) {
            if (c[i] > rem) break;
            path.add(c[i]);
            backtrack(c, rem - c[i], i, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> r = combinationSum(new int[]{2, 3, 6, 7}, 7);
        check(r.size() == 2, "size");
        check(r.contains(Arrays.asList(2, 2, 3)), "combo1");
        check(r.contains(Arrays.asList(7)), "combo2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
