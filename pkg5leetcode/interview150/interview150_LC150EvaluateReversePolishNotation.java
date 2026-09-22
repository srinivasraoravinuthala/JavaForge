package pkg5leetcode.interview150;

/*
 * Evaluate Reverse Polish Notation | LC 150
 * APPROACH: Stack push numbers; pop and apply operators.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class interview150_LC150EvaluateReversePolishNotation {
    static int evalRPN(String[] tokens) {
        Deque<Integer> st = new ArrayDeque<>();
        for (String t : tokens) {
            if (t.length() == 1 && "+-*/".contains(t)) {
                int b = st.pop(), a = st.pop();
                switch (t) {
                    case "+": st.push(a + b); break;
                    case "-": st.push(a - b); break;
                    case "*": st.push(a * b); break;
                    default: st.push(a / b);
                }
            } else st.push(Integer.parseInt(t));
        }
        return st.pop();
    }

    public static void main(String[] args) {
        check(evalRPN(new String[]{"2", "1", "+", "3", "*"}) == 9, "case1");
        check(evalRPN(new String[]{"4", "13", "5", "/", "+"}) == 6, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
