package pkg5leetcode.interview150;

/*
 * Longest Common Prefix | LC 14
 * APPROACH: Compare characters column-wise across all strings.
 * COMPLEXITY: Time O(n*m), Space O(1)
 */
public class interview150_LC14LongestCommonPrefix {
    static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        check(longestCommonPrefix(new String[]{"flower", "flow", "flight"}).equals("fl"), "case1");
        check(longestCommonPrefix(new String[]{"dog", "racecar", "car"}).equals(""), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
