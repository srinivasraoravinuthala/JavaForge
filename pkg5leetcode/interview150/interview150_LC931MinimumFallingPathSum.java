package pkg5leetcode.interview150;

/** LC 931 Minimum Falling Path Sum */
public class interview150_LC931MinimumFallingPathSum {
  static int minFallingPathSum(int[][] matrix) {
    int n = matrix.length;
    for (int r = 1; r < n; r++)
      for (int c = 0; c < n; c++) {
        int best = matrix[r-1][c];
        if (c > 0) best = Math.min(best, matrix[r-1][c-1]);
        if (c + 1 < n) best = Math.min(best, matrix[r-1][c+1]);
        matrix[r][c] += best;
      }
    int ans = matrix[n-1][0];
    for (int c = 1; c < n; c++) ans = Math.min(ans, matrix[n-1][c]);
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}}));
  }
}
