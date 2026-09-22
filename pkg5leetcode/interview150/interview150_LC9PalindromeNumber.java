package pkg5leetcode.interview150;

/*
 * Palindrome Number | LC 9
 * APPROACH: Reverse half of digits; compare with remaining half.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class interview150_LC9PalindromeNumber {
    static boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }

    public static void main(String[] args) {
        check(isPalindrome(121), "case1");
        check(!isPalindrome(-121), "case2");
        check(!isPalindrome(10), "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
