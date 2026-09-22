package pkg5leetcode.official75;

/*
 * Greatest Common Divisor of Strings | LC 1071
 * APPROACH: GCD of lengths; check concatenation symmetry.
 * COMPLEXITY: Time O(n), Space O(n)
 */
public class official75_LC1071GreatestCommonDivisorOfStrings {
    static String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) return "";
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    static int gcd(int a, int b) {
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a;
    }

    public static void main(String[] args) {
        check("ABC".equals(gcdOfStrings("ABCABC", "ABC")), "case1");
        check("AB".equals(gcdOfStrings("ABABAB", "ABAB")), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
