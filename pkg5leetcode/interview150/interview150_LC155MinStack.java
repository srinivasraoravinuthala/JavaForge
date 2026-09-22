package pkg5leetcode.interview150;

/*
 * Min Stack | LC 155
 * APPROACH: Two stacks track current min alongside values.
 * COMPLEXITY: Time O(1) per op, Space O(n)
 */
import java.util.*;

public class interview150_LC155MinStack {
    static class MinStack {
        Deque<Integer> stack = new ArrayDeque<>();
        Deque<Integer> mins = new ArrayDeque<>();

        void push(int val) {
            stack.push(val);
            if (mins.isEmpty() || val <= mins.peek()) mins.push(val);
        }

        void pop() {
            if (stack.pop().equals(mins.peek())) mins.pop();
        }

        int top() { return stack.peek(); }

        int getMin() { return mins.peek(); }
    }

    public static void main(String[] args) {
        MinStack ms = new MinStack();
        ms.push(-2);
        ms.push(0);
        ms.push(-3);
        check(ms.getMin() == -3, "case1");
        ms.pop();
        check(ms.top() == 0, "case2");
        check(ms.getMin() == -2, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
