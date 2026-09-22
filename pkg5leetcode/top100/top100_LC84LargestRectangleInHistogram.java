package pkg5leetcode.top100;

/*
 * Largest Rectangle in Histogram | LC 84
 * APPROACH: Monotonic stack finds width when popping lower bar.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class top100_LC84LargestRectangleInHistogram {
    static int largestRectangleArea(int[] heights) {
        Deque<Integer> st = new ArrayDeque<>();
        int best = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i];
            while (!st.isEmpty() && h < heights[st.peek()]) {
                int height = heights[st.pop()];
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                best = Math.max(best, height * width);
            }
            st.push(i);
        }
        return best;
    }

    public static void main(String[] args) {
        check(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}) == 10, "case1");
        check(largestRectangleArea(new int[]{2, 4}) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
