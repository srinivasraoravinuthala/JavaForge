package pkg5leetcode.blind75;

/*
 * Valid Parentheses | LC 20
 * APPROACH: Stack matches closing bracket to top opening.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class blind75_LC20ValidParentheses {
    static boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') st.push(c);
            else {
                if (st.isEmpty()) return false;
                char o = st.pop();
                if (c == ')' && o != '(' || c == '}' && o != '{' || c == ']' && o != '[') return false;
            }
        }
        return st.isEmpty();
    }

    public static void main(String[] args) {
        check(isValid("()[]{}"), "case1");
        check(!isValid("(]"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
