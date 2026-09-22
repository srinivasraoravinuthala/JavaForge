package pkg5leetcode.blind75;

/*
 * Rotate Image | LC 48
 * APPROACH: Transpose then reverse each row for 90° clockwise.
 * COMPLEXITY: Time O(n^2), Space O(1)
 */
public class blind75_LC48RotateImage {
    static void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++) {
                int t = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = t;
            }
        for (int i = 0; i < n; i++)
            for (int lo = 0, hi = n - 1; lo < hi; lo++, hi--) {
                int t = matrix[i][lo];
                matrix[i][lo] = matrix[i][hi];
                matrix[i][hi] = t;
            }
    }

    public static void main(String[] args) {
        int[][] m1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate(m1);
        check(m1[0][0] == 7 && m1[0][2] == 1 && m1[2][0] == 9, "case1");
        int[][] m2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        rotate(m2);
        check(m2[0][0] == 15 && m2[3][3] == 11, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
