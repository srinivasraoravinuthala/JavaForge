package pkg5leetcode;

/*
 * LeetCode 20: Valid Parentheses  (Easy)
 * --------------------------------------
 * Given a string of brackets, determine if it is validly closed/nested.
 *
 * APPROACH: push opens onto a stack; on a close, the top must be its match.
 * COMPLEXITY: Time O(n), Space O(n).
 */
import java.util.*;

public class leetcode2ValidParentheses {

    static boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> pairs = Map.of(')', '(', ']', '[', '}', '{');
        for (char c : s.toCharArray()) {
            if (pairs.containsValue(c)) {
                stack.push(c);
            } else if (pairs.containsKey(c)) {
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        check(isValid("()"), "()");
        check(isValid("()[]{}"), "()[]{}");
        check(!isValid("(]"), "(]");
        check(!isValid("([)]"), "([)]");
        check(isValid("{[]}"), "{[]}");
        check(!isValid("("), "(");
        System.out.println("leetcode2ValidParentheses: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
