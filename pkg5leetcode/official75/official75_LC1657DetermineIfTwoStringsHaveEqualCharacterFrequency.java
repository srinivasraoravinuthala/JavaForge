package pkg5leetcode.official75;

/*
 * Determine if Two Strings Have Equal Character Frequency | LC 1657
 * APPROACH: Same length and same sorted char frequency arrays.
 * COMPLEXITY: Time O(n), Space O(1)
 */
import java.util.*;

public class official75_LC1657DetermineIfTwoStringsHaveEqualCharacterFrequency {
    static boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) return false;
        int[] c1 = new int[26], c2 = new int[26];
        for (int i = 0; i < word1.length(); i++) {
            c1[word1.charAt(i) - 'a']++;
            c2[word2.charAt(i) - 'a']++;
        }
        for (int i = 0; i < 26; i++)
            if ((c1[i] == 0) != (c2[i] == 0)) return false;
        Arrays.sort(c1);
        Arrays.sort(c2);
        return Arrays.equals(c1, c2);
    }

    public static void main(String[] args) {
        check(closeStrings("abc", "bca"), "case1");
        check(!closeStrings("a", "aa"), "case2");
        check(closeStrings("cabbba", "abbccc"), "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
