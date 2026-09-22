package pkg5leetcode.blind75;

/*
 * Pacific Atlantic Water Flow | LC 417
 * APPROACH: Reverse DFS/BFS from ocean borders; intersection cells drain both.
 * COMPLEXITY: Time O(mn), Space O(mn)
 */
import java.util.*;

public class blind75_LC417PacificAtlanticWaterFlow {
    static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length, n = heights[0].length;
        boolean[][] pac = new boolean[m][n], atl = new boolean[m][n];
        for (int i = 0; i < m; i++) { dfs(heights, pac, i, 0); dfs(heights, atl, i, n - 1); }
        for (int j = 0; j < n; j++) { dfs(heights, pac, 0, j); dfs(heights, atl, m - 1, j); }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (pac[i][j] && atl[i][j]) res.add(Arrays.asList(i, j));
        return res;
    }

    static void dfs(int[][] h, boolean[][] vis, int r, int c) {
        vis[r][c] = true;
        int[][] d = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dd : d) {
            int nr = r + dd[0], nc = c + dd[1];
            if (nr >= 0 && nr < h.length && nc >= 0 && nc < h[0].length
                    && !vis[nr][nc] && h[nr][nc] >= h[r][c])
                dfs(h, vis, nr, nc);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        List<List<Integer>> r = pacificAtlantic(grid);
        check(r.size() == 7, "size");
        check(r.contains(Arrays.asList(0, 4)), "cell1");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
