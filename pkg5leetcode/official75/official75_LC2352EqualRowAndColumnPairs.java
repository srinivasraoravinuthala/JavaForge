package pkg5leetcode.official75;

/*
 * Equal Row and Column Pairs | LC 2352
 * APPROACH: Hash row signatures; count matching columns.
 * COMPLEXITY: Time O(n^2), Space O(n^2)
 */
import java.util.*;

public class official75_LC2352EqualRowAndColumnPairs {
    static int equalPairs(int[][] grid) {
        int n = grid.length, count = 0;
        Map<String, Integer> rows = new HashMap<>();
        for (int[] r : grid) {
            String key = Arrays.toString(r);
            rows.put(key, rows.getOrDefault(key, 0) + 1);
        }
        for (int c = 0; c < n; c++) {
            int[] col = new int[n];
            for (int r = 0; r < n; r++) col[r] = grid[r][c];
            count += rows.getOrDefault(Arrays.toString(col), 0);
        }
        return count;
    }

    public static void main(String[] args) {
        check(equalPairs(new int[][]{{3,2,1},{1,7,6},{2,7,7}}) == 1, "case1");
        check(equalPairs(new int[][]{{3,1,2,2},{1,4,4,5},{2,4,2,2},{2,4,2,2}}) == 3, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
