package pkg5leetcode.top100;

/*
 * Permutations | LC 46
 * APPROACH: Backtrack swap elements to generate all orderings.
 * COMPLEXITY: Time O(n*n!), Space O(n)
 */
import java.util.*;

public class top100_LC46Permutations {
    static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, res);
        return res;
    }

    static void backtrack(int[] nums, int start, List<List<Integer>> res) {
        if (start == nums.length) {
            res.add(Arrays.stream(nums).boxed().collect(java.util.stream.Collectors.toList()));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            backtrack(nums, start + 1, res);
            swap(nums, start, i);
        }
    }

    static void swap(int[] a, int i, int j) { int t = a[i]; a[i] = a[j]; a[j] = t; }

    public static void main(String[] args) {
        check(permute(new int[]{1, 2, 3}).size() == 6, "case1");
        check(permute(new int[]{0, 1}).size() == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
