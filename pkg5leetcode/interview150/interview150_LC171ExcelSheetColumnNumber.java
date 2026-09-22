package pkg5leetcode.interview150;

/*
 * Excel Sheet Column Number | LC 171
 * APPROACH: Base-26 from right; A=1 not zero-based.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC171ExcelSheetColumnNumber {
    static int titleToNumber(String columnTitle) {
        int res = 0;
        for (char c : columnTitle.toCharArray())
            res = res * 26 + (c - 'A' + 1);
        return res;
    }

    public static void main(String[] args) {
        check(titleToNumber("A") == 1, "case1");
        check(titleToNumber("AB") == 28, "case2");
        check(titleToNumber("ZY") == 701, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
