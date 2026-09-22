package pkg5leetcode.top100;

/*
 * Letter Combinations of a Phone Number | LC 17
 * APPROACH: Backtrack building strings digit by digit.
 * COMPLEXITY: Time O(4^n), Space O(n)
 */
import java.util.*;

public class top100_LC17LetterCombinationsOfAPhoneNumber {
    static final String[] MAP = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    static void backtrack(String d, int i, StringBuilder sb, List<String> res) {
        if (i == d.length()) { res.add(sb.toString()); return; }
        for (char c : MAP[d.charAt(i) - '0'].toCharArray()) {
            sb.append(c);
            backtrack(d, i + 1, sb, res);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        check(letterCombinations("23").size() == 9, "case1");
        check(letterCombinations("").isEmpty(), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
