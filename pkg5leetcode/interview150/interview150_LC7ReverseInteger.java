package pkg5leetcode.interview150;

/*
 * Reverse Integer | LC 7
 * APPROACH: Pop/push digits; check overflow before multiplying by 10.
 * COMPLEXITY: Time O(log n), Space O(1)
 */
public class interview150_LC7ReverseInteger {
    static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }

    public static void main(String[] args) {
        check(reverse(123) == 321, "case1");
        check(reverse(-123) == -321, "case2");
        check(reverse(120) == 21, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
