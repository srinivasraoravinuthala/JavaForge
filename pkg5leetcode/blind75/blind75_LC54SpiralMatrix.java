package pkg5leetcode.blind75;

/*
 * Spiral Matrix | LC 54
 * APPROACH: Layer-by-layer traverse top/bottom/left/right bounds.
 * COMPLEXITY: Time O(mn), Space O(1) excluding output
 */
import java.util.*;

public class blind75_LC54SpiralMatrix {
    static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (top <= bottom && left <= right) {
            for (int j = left; j <= right; j++) res.add(matrix[top][j]);
            top++;
            for (int i = top; i <= bottom; i++) res.add(matrix[i][right]);
            right--;
            if (top <= bottom) {
                for (int j = right; j >= left; j--) res.add(matrix[bottom][j]);
                bottom--;
            }
            if (left <= right) {
                for (int i = bottom; i >= top; i--) res.add(matrix[i][left]);
                left++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        check(spiralOrder(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}).equals(Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5)), "case1");
        check(spiralOrder(new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}}).equals(Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7)), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
