package pkg5leetcode.interview150;

/*
 * Flood Fill | LC 733
 * APPROACH: DFS/BFS paint connected same-color cells.
 * COMPLEXITY: Time O(m*n), Space O(m*n)
 */
public class interview150_LC733FloodFill {
    static int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int orig = image[sr][sc];
        if (orig == color) return image;
        dfs(image, sr, sc, orig, color);
        return image;
    }

    static void dfs(int[][] img, int r, int c, int orig, int color) {
        if (r < 0 || c < 0 || r >= img.length || c >= img[0].length || img[r][c] != orig) return;
        img[r][c] = color;
        dfs(img, r + 1, c, orig, color);
        dfs(img, r - 1, c, orig, color);
        dfs(img, r, c + 1, orig, color);
        dfs(img, r, c - 1, orig, color);
    }

    public static void main(String[] args) {
        int[][] img = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] res = floodFill(img, 1, 1, 2);
        check(res[1][1] == 2 && res[0][0] == 2, "case1");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
