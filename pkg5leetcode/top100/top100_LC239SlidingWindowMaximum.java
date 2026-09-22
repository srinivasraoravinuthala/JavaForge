package pkg5leetcode.top100;

/*
 * Sliding Window Maximum | LC 239
 * APPROACH: Monotonic deque stores indices of decreasing values.
 * COMPLEXITY: Time O(n), Space O(k)
 */
import java.util.*;

public class top100_LC239SlidingWindowMaximum {
    static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> dq = new ArrayDeque<>();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) dq.pollFirst();
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast();
            dq.offerLast(i);
            if (i >= k - 1) res[i - k + 1] = nums[dq.peekFirst()];
        }
        return res;
    }

    public static void main(String[] args) {
        check(java.util.Arrays.equals(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3),
            new int[]{3, 3, 5, 5, 6, 7}), "case1");
        check(java.util.Arrays.equals(maxSlidingWindow(new int[]{1}, 1), new int[]{1}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
