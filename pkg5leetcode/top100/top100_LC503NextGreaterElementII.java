package pkg5leetcode.top100;

/*
 * Next Greater Element II | LC 503
 * APPROACH: Monotonic stack on circular array (double scan).
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class top100_LC503NextGreaterElementII {
    static int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> st = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (!st.isEmpty() && nums[st.peek()] < nums[idx]) res[st.pop()] = nums[idx];
            if (i < n) st.push(idx);
        }
        return res;
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(nextGreaterElements(new int[]{1, 2, 1}), new int[]{2, -1, 2}), "case1");
        check(java.util.Arrays.equals(nextGreaterElements(new int[]{1, 2, 3, 4, 3}), new int[]{2, 3, 4, -1, 4}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
