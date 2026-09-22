package pkg4algorithms;

/*
 * algorithms5Backtracking.java
 * -----------------
 * Build candidates incrementally and abandon ("backtrack") a path as soon as it
 * cannot lead to a valid solution. Template: choose -> explore -> un-choose.
 *
 * Covered: subsets (power set), permutations, combinations, and N-Queens count.
 */
import java.util.*;

public class algorithms5Backtracking {

    static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackSubsets(nums, 0, new ArrayList<>(), res);
        return res;
    }
    static void backtrackSubsets(int[] nums, int start, List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));               // every prefix is a subset
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);                        // choose
            backtrackSubsets(nums, i + 1, path, res); // explore
            path.remove(path.size() - 1);             // un-choose
        }
    }

    static List<List<Integer>> permutations(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackPerm(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }
    static void backtrackPerm(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == nums.length) { res.add(new ArrayList<>(path)); return; }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue;
            used[i] = true; path.add(nums[i]);
            backtrackPerm(nums, used, path, res);
            used[i] = false; path.remove(path.size() - 1);
        }
    }

    static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrackComb(1, n, k, new ArrayList<>(), res);
        return res;
    }
    static void backtrackComb(int start, int n, int k, List<Integer> path, List<List<Integer>> res) {
        if (path.size() == k) { res.add(new ArrayList<>(path)); return; }
        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrackComb(i + 1, n, k, path, res);
            path.remove(path.size() - 1);
        }
    }

    // N-Queens: count distinct ways to place n non-attacking queens.
    static int nQueens(int n) {
        return placeQueen(0, n, new boolean[n], new boolean[2 * n], new boolean[2 * n]);
    }
    static int placeQueen(int row, int n, boolean[] cols, boolean[] diag, boolean[] anti) {
        if (row == n) return 1;
        int count = 0;
        for (int col = 0; col < n; col++) {
            int d = row - col + n, a = row + col;
            if (cols[col] || diag[d] || anti[a]) continue;       // pruning
            cols[col] = diag[d] = anti[a] = true;
            count += placeQueen(row + 1, n, cols, diag, anti);
            cols[col] = diag[d] = anti[a] = false;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("subsets([1,2,3]) = " + subsets(new int[]{1, 2, 3}));
        System.out.println("permutations([1,2,3]) = " + permutations(new int[]{1, 2, 3}));
        System.out.println("combinations(4,2) = " + combinations(4, 2));
        System.out.println("N-Queens solutions for n=8: " + nQueens(8));   // expected 92
    }
}
