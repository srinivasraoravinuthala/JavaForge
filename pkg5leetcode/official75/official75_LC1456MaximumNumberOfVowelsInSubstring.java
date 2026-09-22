package pkg5leetcode.official75;

/*
 * Maximum Number of Vowels in a Substring | LC 1456
 * APPROACH: Sliding window count vowels up to size k.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC1456MaximumNumberOfVowelsInSubstring {
    static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    static int maxVowels(String s, int k) {
        int count = 0, best = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) count++;
            if (i >= k && isVowel(s.charAt(i - k))) count--;
            if (i >= k - 1) best = Math.max(best, count);
        }
        return best;
    }

    public static void main(String[] args) {
        check(maxVowels("abciiidef", 3) == 3, "case1");
        check(maxVowels("leetcode", 3) == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
