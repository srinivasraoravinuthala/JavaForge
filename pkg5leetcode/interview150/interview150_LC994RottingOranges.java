package pkg5leetcode.interview150;

/*
 * Rotting Oranges | LC 994
 * APPROACH: Multi-source BFS from rotten oranges; count minutes.
 * COMPLEXITY: Time O(m*n), Space O(m*n)
 */
import java.util.*;

public class interview150_LC994RottingOranges {
    static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length, fresh = 0;
        Deque<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 2) q.add(new int[]{r, c});
                else if (grid[r][c] == 1) fresh++;
            }
        }
        int mins = 0;
        while (!q.isEmpty() && fresh > 0) {
            int size = q.size();
            for (int s = 0; s < size; s++) {
                int[] p = q.poll();
                for (int[] d : new int[][]{{1,0},{-1,0},{0,1},{0,-1}}) {
                    int nr = p[0] + d[0], nc = p[1] + d[1];
                    if (nr >= 0 && nc >= 0 && nr < m && nc < n && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        fresh--;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            mins++;
        }
        return fresh == 0 ? mins : -1;
    }

    public static void main(String[] args) {
        check(orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}) == 4, "case1");
        check(orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}}) == -1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
