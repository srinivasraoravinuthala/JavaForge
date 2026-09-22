package pkg5leetcode.interview150;

/*
 * Reverse String | LC 344
 * APPROACH: Two pointers swap chars from both ends.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC344ReverseString {
    static void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char t = s[l];
            s[l++] = s[r];
            s[r--] = t;
        }
    }

    public static void main(String[] args) {
        char[] a = {'h', 'e', 'l', 'l', 'o'};
        reverseString(a);
        check(new String(a).equals("olleh"), "case1");
        char[] b = {'H', 'a', 'n', 'n', 'a', 'h'};
        reverseString(b);
        check(new String(b).equals("hannaH"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
