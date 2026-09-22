package pkg5leetcode.official75;

/*
 * Removing Stars From a String | LC 2390
 * APPROACH: Stack push chars; pop on star.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC2390RemovingStarsFromAString {
    static String removeStars(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '*') st.pollLast();
            else st.addLast(c);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : st) sb.append(c);
        return sb.toString();
    }

    public static void main(String[] args) {
        check("le".equals(removeStars("leet**")), "case1");
        check("".equals(removeStars("erase*****")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
