package pkg5leetcode.top100;

/*
 * Search a 2D Matrix II | LC 240
 * APPROACH: Start top-right; move left or down based on comparison.
 * COMPLEXITY: Time O(m+n), Space O(1)
 */
public class top100_LC240SearchA2DMatrixII {
    static boolean searchMatrix(int[][] matrix, int target) {
        int r = 0, c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) return true;
            if (matrix[r][c] > target) c--;
            else r++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] m = {{1,4,7,11,15},{10,11,16,20,23},{23,30,34,60}};
        check(searchMatrix(m, 5), "case1");
        check(!searchMatrix(m, 13), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
