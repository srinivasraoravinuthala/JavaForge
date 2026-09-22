package pkg5leetcode.interview150;

/*
 * Valid Sudoku | LC 36
 * APPROACH: Hash sets for rows, columns, and 3x3 boxes.
 * COMPLEXITY: Time O(81), Space O(81)
 */
import java.util.*;

public class interview150_LC36ValidSudoku {
    static boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char ch = board[r][c];
                if (ch == '.') continue;
                String key = ch + "r" + r + "c" + c + "b" + r / 3 + c / 3;
                if (!seen.add(key)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] valid = {
            {"5","3",".",".","7",".",".",".","."},
            {"6",".",".","1","9","5",".",".","."},
            {".","9","8",".",".",".",".","6","."},
            {"8",".",".",".","6",".",".",".","3"},
            {"4",".",".","8",".","3",".",".","1"},
            {"7",".",".",".","2",".",".",".","6"},
            {".","6",".",".",".",".","2","8","."},
            {".",".",".","4","1","9",".",".","5"},
            {".",".",".",".","8",".",".","7","9"}
        };
        check(isValidSudoku(valid), "case1");
        char[][] invalid = {
            {"8","3",".",".","7",".",".",".","."},
            {"6",".",".","1","9","5",".",".","."},
            {".","9","8",".",".",".",".","6","."},
            {"8",".",".",".","6",".",".",".","3"},
            {"4",".",".","8",".","3",".",".","1"},
            {"7",".",".",".","2",".",".",".","6"},
            {".","6",".",".",".",".","2","8","."},
            {".",".",".","4","1","9",".",".","5"},
            {".",".",".",".","8",".",".","7","9"}
        };
        check(!isValidSudoku(invalid), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
