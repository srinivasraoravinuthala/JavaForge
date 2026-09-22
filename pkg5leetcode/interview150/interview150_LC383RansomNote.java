package pkg5leetcode.interview150;

/*
 * Ransom Note | LC 383
 * APPROACH: Count magazine chars; decrement for ransomNote.
 * COMPLEXITY: Time O(m+n), Space O(1)
 */
public class interview150_LC383RansomNote {
    static boolean canConstruct(String ransomNote, String magazine) {
        int[] cnt = new int[26];
        for (char c : magazine.toCharArray()) cnt[c - 'a']++;
        for (char c : ransomNote.toCharArray()) {
            if (--cnt[c - 'a'] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        check(!canConstruct("a", "b"), "case1");
        check(!canConstruct("aa", "ab"), "case2");
        check(canConstruct("aa", "aab"), "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
