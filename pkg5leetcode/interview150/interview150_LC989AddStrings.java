package pkg5leetcode.interview150;

/*
 * Add Strings | LC 989
 * APPROACH: Add digits from end with carry like grade-school addition.
 * COMPLEXITY: Time O(n), Space O(n)
 */
public class interview150_LC989AddStrings {
    static String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += num1.charAt(i--) - '0';
            if (j >= 0) sum += num2.charAt(j--) - '0';
            sb.append(sum % 10);
            carry = sum / 10;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        check(addStrings("11", "123").equals("134"), "case1");
        check(addStrings("456", "77").equals("533"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
