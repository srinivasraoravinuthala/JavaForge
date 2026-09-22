package pkg5leetcode.official75;

/*
 * Daily Temperatures | LC 739
 * APPROACH: Monotonic decreasing stack of indices.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC739DailyTemperatures {
    static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && temperatures[i] > temperatures[st.peekLast()]) {
                int idx = st.pollLast();
                res[idx] = i - idx;
            }
            st.addLast(i);
        }
        return res;
    }

    public static void main(String[] args) {
        check(Arrays.equals(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}),
                new int[]{1,1,4,2,1,1,0,0}), "case1");
        check(Arrays.equals(dailyTemperatures(new int[]{30,40,50,60}),
                new int[]{1,1,1,0}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
