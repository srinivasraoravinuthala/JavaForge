package pkg5leetcode.official75;

/*
 * Letter Combinations of a Phone Number | LC 17
 * APPROACH: Backtracking map digits to letters.
 * COMPLEXITY: Time O(4^n), Space O(n)
 */
import java.util.*;

public class official75_LC17LetterCombinationsOfAPhoneNumber {
    static final String[] MAP = {"","", "abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.isEmpty()) return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }

    static void backtrack(String digits, int i, StringBuilder path, List<String> res) {
        if (i == digits.length()) { res.add(path.toString()); return; }
        for (char c : MAP[digits.charAt(i) - '0'].toCharArray()) {
            path.append(c);
            backtrack(digits, i + 1, path, res);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> r = letterCombinations("23");
        check(r.size() == 9 && r.contains("ad") && r.contains("cf"), "case1");
        check(letterCombinations("").isEmpty(), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
