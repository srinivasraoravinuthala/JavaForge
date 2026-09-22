package pkg5leetcode.top100;

/*
 * Generate Parentheses | LC 22
 * APPROACH: Backtrack add '(' or ')' while valid counts.
 * COMPLEXITY: Time O(4^n/sqrt(n)), Space O(n)
 */
import java.util.*;

public class top100_LC22GenerateParentheses {
    static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(n, 0, 0, new StringBuilder(), res);
        return res;
    }

    static void backtrack(int n, int open, int close, StringBuilder sb, List<String> res) {
        if (sb.length() == 2 * n) { res.add(sb.toString()); return; }
        if (open < n) {
            sb.append('(');
            backtrack(n, open + 1, close, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (close < open) {
            sb.append(')');
            backtrack(n, open, close + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        check(generateParenthesis(3).size() == 5, "case1");
        check(generateParenthesis(1).equals(Arrays.asList("()")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
