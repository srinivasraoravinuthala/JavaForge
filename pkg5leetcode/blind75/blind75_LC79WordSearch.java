package pkg5leetcode.blind75;

/*
 * Word Search | LC 79
 * APPROACH: DFS backtracking from each cell with visited marking.
 * COMPLEXITY: Time O(mn * 4^L), Space O(L)
 */
public class blind75_LC79WordSearch {
    static boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (dfs(board, word, i, j, 0)) return true;
        return false;
    }

    static boolean dfs(char[][] b, String w, int r, int c, int idx) {
        if (idx == w.length()) return true;
        if (r < 0 || c < 0 || r >= b.length || c >= b[0].length || b[r][c] != w.charAt(idx)) return false;
        char tmp = b[r][c];
        b[r][c] = '#';
        boolean found = dfs(b, w, r + 1, c, idx + 1) || dfs(b, w, r - 1, c, idx + 1)
                || dfs(b, w, r, c + 1, idx + 1) || dfs(b, w, r, c - 1, idx + 1);
        b[r][c] = tmp;
        return found;
    }

    public static void main(String[] args) {
        check(exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCCED"), "case1");
        check(!exist(new char[][]{{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}}, "ABCB"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
