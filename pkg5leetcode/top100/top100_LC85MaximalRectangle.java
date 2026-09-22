package pkg5leetcode.top100;

/*
 * Maximal Rectangle | LC 85
 * APPROACH: Treat each row as histogram base; run LC84 on each row.
 * COMPLEXITY: Time O(m*n), Space O(n)
 */
import java.util.*;

public class top100_LC85MaximalRectangle {
    static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] h = new int[n], best = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++)
                h[c] = matrix[r][c] == '1' ? h[c] + 1 : 0;
            best = Math.max(best, largestRect(h));
        }
        return best;
    }

    static int largestRect(int[] heights) {
        Deque<Integer> st = new ArrayDeque<>();
        int best = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            while (!st.isEmpty() && h < heights[st.peek()]) {
                int height = heights[st.pop()];
                int w = st.isEmpty() ? i : i - st.peek() - 1;
                best = Math.max(best, height * w);
            }
            st.push(i);
        }
        return best;
    }

    public static void main(String[] args) {
        char[][] m = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        check(maximalRectangle(m) == 6, "case1");
        check(maximalRectangle(new char[][]{{'0'}}) == 0, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
