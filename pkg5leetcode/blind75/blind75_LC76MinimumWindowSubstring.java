package pkg5leetcode.blind75;

/*
 * Minimum Window Substring | LC 76
 * APPROACH: Expand right until valid; shrink left while valid.
 * COMPLEXITY: Time O(n), Space O(1) alphabet
 */
public class blind75_LC76MinimumWindowSubstring {
    static String minWindow(String s, String t) {
        if (t.length() > s.length()) return "";
        int[] need = new int[128], have = new int[128];
        for (char c : t.toCharArray()) need[c]++;
        int required = t.length(), formed = 0;
        int left = 0, bestLen = Integer.MAX_VALUE, bestStart = 0;
        for (int right = 0; right < s.length(); right++) {
            char rc = s.charAt(right);
            if (need[rc] > 0 && ++have[rc] <= need[rc]) formed++;
            while (formed == required) {
                if (right - left + 1 < bestLen) { bestLen = right - left + 1; bestStart = left; }
                char lc = s.charAt(left++);
                if (need[lc] > 0 && --have[lc] < need[lc]) formed--;
            }
        }
        return bestLen == Integer.MAX_VALUE ? "" : s.substring(bestStart, bestStart + bestLen);
    }

    public static void main(String[] args) {
        check(minWindow("ADOBECODEBANC", "ABC").equals("BANC"), "case1");
        check(minWindow("a", "a").equals("a"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
