package pkg5leetcode.official75;

/*
 * Decode String | LC 394
 * APPROACH: Stack push current string and repeat count on '['.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC394DecodeString {
    static String decodeString(String s) {
        Deque<StringBuilder> strSt = new ArrayDeque<>();
        Deque<Integer> numSt = new ArrayDeque<>();
        StringBuilder cur = new StringBuilder();
        int k = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) k = k * 10 + (c - '0');
            else if (c == '[') {
                numSt.addLast(k);
                strSt.addLast(cur);
                cur = new StringBuilder();
                k = 0;
            } else if (c == ']') {
                StringBuilder prev = strSt.removeLast();
                int rep = numSt.removeLast();
                for (int i = 0; i < rep; i++) prev.append(cur);
                cur = prev;
            } else cur.append(c);
        }
        return cur.toString();
    }

    public static void main(String[] args) {
        check("aaabcbc".equals(decodeString("3[a]2[bc]")), "case1");
        check("accaccacc".equals(decodeString("3[a2[c]]")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
