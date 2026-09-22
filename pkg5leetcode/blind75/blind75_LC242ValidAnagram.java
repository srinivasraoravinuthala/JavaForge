package pkg5leetcode.blind75;

/*
 * Valid Anagram | LC 242
 * APPROACH: Frequency count arrays for both strings.
 * COMPLEXITY: Time O(n), Space O(26)
 */
public class blind75_LC242ValidAnagram {
    static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i) - 'a']++;
            cnt[t.charAt(i) - 'a']--;
        }
        for (int x : cnt) if (x != 0) return false;
        return true;
    }

    public static void main(String[] args) {
        check(isAnagram("anagram", "nagaram"), "case1");
        check(!isAnagram("rat", "car"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
