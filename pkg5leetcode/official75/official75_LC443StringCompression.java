package pkg5leetcode.official75;

/*
 * String Compression | LC 443
 * APPROACH: In-place write char then count digits.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class official75_LC443StringCompression {
    static int compress(char[] chars) {
        int write = 0, read = 0;
        while (read < chars.length) {
            char c = chars[read];
            int count = 0;
            while (read < chars.length && chars[read] == c) { read++; count++; }
            chars[write++] = c;
            if (count > 1) {
                for (char d : String.valueOf(count).toCharArray()) chars[write++] = d;
            }
        }
        return write;
    }

    public static void main(String[] args) {
        char[] a = {'a','a','b','b','c','c','c'};
        check(compress(a) == 6 && a[0]=='a' && a[1]=='2' && a[2]=='b', "case1");
        char[] b = {'a'};
        check(compress(b) == 1 && b[0]=='a', "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
