package pkg5leetcode.top100;

/*
 * N-Queens | LC 51
 * APPROACH: Backtrack place queens row by row checking columns/diagonals.
 * COMPLEXITY: Time O(n!), Space O(n^2)
 */
import java.util.*;

public class top100_LC51NQueens {
    static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) java.util.Arrays.fill(row, '.');
        backtrack(board, 0, res);
        return res;
    }

    static void backtrack(char[][] b, int r, List<List<String>> res) {
        if (r == b.length) {
            List<String> sol = new ArrayList<>();
            for (char[] row : b) sol.add(new String(row));
            res.add(sol);
            return;
        }
        for (int c = 0; c < b.length; c++) {
            if (!ok(b, r, c)) continue;
            b[r][c] = 'Q';
            backtrack(b, r + 1, res);
            b[r][c] = '.';
        }
    }

    static boolean ok(char[][] b, int r, int c) {
        for (int i = 0; i < r; i++) if (b[i][c] == 'Q') return false;
        for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--)
            if (b[i][j] == 'Q') return false;
        for (int i = r - 1, j = c + 1; i >= 0 && j < b.length; i--, j++)
            if (b[i][j] == 'Q') return false;
        return true;
    }

    public static void main(String[] args) {
        check(solveNQueens(4).size() == 2, "case1");
        check(solveNQueens(1).size() == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
