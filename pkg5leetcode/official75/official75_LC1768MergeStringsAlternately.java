package pkg5leetcode.official75;

/*
 * Merge Strings Alternately | LC 1768
 * APPROACH: Two pointers append from word1 and word2 alternately.
 * COMPLEXITY: Time O(m+n), Space O(m+n)
 */
public class official75_LC1768MergeStringsAlternately {
    static String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < word1.length() || j < word2.length()) {
            if (i < word1.length()) sb.append(word1.charAt(i++));
            if (j < word2.length()) sb.append(word2.charAt(j++));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        check("apbqcr".equals(mergeAlternately("abc", "pqr")), "case1");
        check("apbqrs".equals(mergeAlternately("ab", "pqrs")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
