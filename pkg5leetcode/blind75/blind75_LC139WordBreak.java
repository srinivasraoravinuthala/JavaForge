package pkg5leetcode.blind75;

/*
 * Word Break | LC 139
 * APPROACH: DP dp[i]=true if prefix s[0..i) can be segmented.
 * COMPLEXITY: Time O(n^2 * words), Space O(n)
 */
import java.util.*;

public class blind75_LC139WordBreak {
    static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++)
            for (int j = 0; j < i; j++)
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
        return dp[s.length()];
    }

    public static void main(String[] args) {
        check(wordBreak("leetcode", Arrays.asList("leet", "code")), "case1");
        check(!wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
