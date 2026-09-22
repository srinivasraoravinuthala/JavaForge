package pkg5leetcode.official75;

/*
 * Combination Sum III | LC 216
 * APPROACH: Backtracking choose k distinct digits sum to n.
 * COMPLEXITY: Time O(C(9,k)), Space O(k)
 */
import java.util.*;

public class official75_LC216CombinationSumIII {
    static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), res);
        return res;
    }

    static void backtrack(int start, int k, int remain, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) {
            if (remain == 0) res.add(new ArrayList<>(path));
            return;
        }
        for (int d = start; d <= 9; d++) {
            if (remain < d) break;
            path.add(d);
            backtrack(d + 1, k, remain - d, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> r = combinationSum3(3, 7);
        check(r.size() == 1 && r.get(0).equals(Arrays.asList(1,2,4)), "case1");
        check(combinationSum3(3, 9).size() == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
