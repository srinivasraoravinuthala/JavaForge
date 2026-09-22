package pkg5leetcode.interview150;

/*
 * Length of Last Word | LC 58
 * APPROACH: Scan from end skipping trailing spaces; count last word.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC58LengthOfLastWord {
    static int lengthOfLastWord(String s) {
        int i = s.length() - 1;
        while (i >= 0 && s.charAt(i) == ' ') i--;
        int len = 0;
        while (i >= 0 && s.charAt(i) != ' ') { len++; i--; }
        return len;
    }

    public static void main(String[] args) {
        check(lengthOfLastWord("Hello World") == 5, "case1");
        check(lengthOfLastWord("   fly me   to   the moon  ") == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
