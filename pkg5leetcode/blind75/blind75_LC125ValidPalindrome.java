package pkg5leetcode.blind75;

/*
 * Valid Palindrome | LC 125
 * APPROACH: Two pointers skip non-alphanumeric, compare lowercased.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class blind75_LC125ValidPalindrome {
    static boolean isPalindrome(String s) {
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(lo))) lo++;
            while (lo < hi && !Character.isLetterOrDigit(s.charAt(hi))) hi--;
            if (Character.toLowerCase(s.charAt(lo)) != Character.toLowerCase(s.charAt(hi))) return false;
            lo++; hi--;
        }
        return true;
    }

    public static void main(String[] args) {
        check(isPalindrome("A man, a plan, a canal: Panama"), "case1");
        check(!isPalindrome("race a car"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
