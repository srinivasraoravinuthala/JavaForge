package pkg5leetcode.official75;

/*
 * Rotting Oranges | LC 994
 * APPROACH: Multi-source BFS spread rot minute by minute.
 * COMPLEXITY: Time O(mn), Space O(mn)
 */
import java.util.*;

public class official75_LC994RottingOranges {
    static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length, fresh = 0, minutes = 0;
        Deque<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < m; r++)
            for (int c = 0; c < n; c++)
                if (grid[r][c] == 2) q.add(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty() && fresh > 0) {
            minutes++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] d : dirs) {
                    int r = cur[0] + d[0], c = cur[1] + d[1];
                    if (r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        fresh--;
                        q.add(new int[]{r, c});
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        check(orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}) == 4, "case1");
        check(orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}}) == -1, "case2");
        check(orangesRotting(new int[][]{{0,2}}) == 0, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
