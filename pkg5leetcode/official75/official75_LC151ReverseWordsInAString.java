package pkg5leetcode.official75;

/*
 * Reverse Words in a String | LC 151
 * APPROACH: Split on spaces, reverse order, join.
 * COMPLEXITY: Time O(n), Space O(n)
 */
public class official75_LC151ReverseWordsInAString {
    static String reverseWords(String s) {
        String[] parts = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = parts.length - 1; i >= 0; i--) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(parts[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        check("blue is sky the".equals(reverseWords("the sky is blue")), "case1");
        check("world hello".equals(reverseWords("  hello world  ")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
