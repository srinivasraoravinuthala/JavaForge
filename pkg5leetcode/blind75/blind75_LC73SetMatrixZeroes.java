package pkg5leetcode.blind75;

/*
 * Set Matrix Zeroes | LC 73
 * APPROACH: Use first row/col as markers; handle row0/col0 separately.
 * COMPLEXITY: Time O(mn), Space O(1)
 */
public class blind75_LC73SetMatrixZeroes {
    static void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean row0 = false, col0 = false;
        for (int j = 0; j < n; j++) if (matrix[0][j] == 0) row0 = true;
        for (int i = 0; i < m; i++) if (matrix[i][0] == 0) col0 = true;
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][j] == 0) { matrix[i][0] = 0; matrix[0][j] = 0; }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++)
                if (matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
        if (row0) for (int j = 0; j < n; j++) matrix[0][j] = 0;
        if (col0) for (int i = 0; i < m; i++) matrix[i][0] = 0;
    }

    public static void main(String[] args) {
        int[][] m1 = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroes(m1);
        check(m1[1][0] == 0 && m1[1][2] == 0, "case1");
        int[][] m2 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 0, 5}};
        setZeroes(m2);
        check(m2[0][0] == 0 && m2[2][2] == 0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
