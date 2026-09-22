package pkg5leetcode.official75;

/*
 * Is Subsequence | LC 392
 * APPROACH: Two pointers match chars of t in s.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC392IsSubsequence {
    static boolean isSubsequence(String s, String t) {
        int i = 0;
        for (int j = 0; j < t.length() && i < s.length(); j++)
            if (s.charAt(i) == t.charAt(j)) i++;
        return i == s.length();
    }

    public static void main(String[] args) {
        check(isSubsequence("abc", "ahbgdc"), "case1");
        check(!isSubsequence("axc", "ahbgdc"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
