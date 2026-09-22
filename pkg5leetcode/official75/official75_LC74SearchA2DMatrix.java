package pkg5leetcode.official75;

/*
 * Search a 2D Matrix | LC 74
 * APPROACH: Binary search treat matrix as sorted 1D array.
 * COMPLEXITY: Time O(log(mn)), Space O(1)
 */
public class official75_LC74SearchA2DMatrix {
    static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m * n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int val = matrix[mid / n][mid % n];
            if (val == target) return true;
            if (val < target) lo = mid + 1;
            else hi = mid - 1;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] m = {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        check(searchMatrix(m, 3), "case1");
        check(!searchMatrix(m, 13), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
