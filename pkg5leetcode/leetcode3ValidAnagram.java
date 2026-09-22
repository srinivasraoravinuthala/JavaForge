package pkg5leetcode;

/*
 * LeetCode 242: Valid Anagram  (Easy)
 * -----------------------------------
 * Return true if t is an anagram of s.
 *
 * APPROACH: count characters; all counts must cancel to zero.
 * COMPLEXITY: Time O(n), Space O(1) for fixed lowercase alphabet.
 */
public class leetcode3ValidAnagram {

    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int c : count) if (c != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        check(isAnagram("anagram", "nagaram"), "anagram");
        check(!isAnagram("rat", "car"), "rat/car");
        check(!isAnagram("a", "ab"), "different length");
        check(isAnagram("", ""), "empty");
        System.out.println("leetcode3ValidAnagram: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
