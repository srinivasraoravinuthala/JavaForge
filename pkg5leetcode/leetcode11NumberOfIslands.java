package pkg5leetcode;

/*
 * LeetCode 200: Number of Islands  (Medium)
 * -----------------------------------------
 * Count connected components of '1's in a grid (4-directional).
 *
 * APPROACH: scan the grid; on each unvisited '1', flood-fill (DFS) to sink the island.
 * COMPLEXITY: Time O(rows*cols), Space O(rows*cols) worst-case recursion.
 */
public class leetcode11NumberOfIslands {

    static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;
        for (int r = 0; r < grid.length; r++)
            for (int c = 0; c < grid[0].length; c++)
                if (grid[r][c] == '1') { sink(grid, r, c); count++; }
        return count;
    }

    static void sink(char[][] g, int r, int c) {
        if (r < 0 || c < 0 || r >= g.length || c >= g[0].length || g[r][c] != '1') return;
        g[r][c] = '0';                          // mark visited
        sink(g, r + 1, c); sink(g, r - 1, c);
        sink(g, r, c + 1); sink(g, r, c - 1);
    }

    static char[][] grid(String... rows) {
        char[][] g = new char[rows.length][];
        for (int i = 0; i < rows.length; i++) g[i] = rows[i].toCharArray();
        return g;
    }

    public static void main(String[] args) {
        check(numIslands(grid("11110", "11010", "11000", "00000")) == 1, "one island");
        check(numIslands(grid("11000", "11000", "00100", "00011")) == 3, "three islands");
        check(numIslands(grid("000", "000")) == 0, "no island");
        System.out.println("leetcode11NumberOfIslands: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
