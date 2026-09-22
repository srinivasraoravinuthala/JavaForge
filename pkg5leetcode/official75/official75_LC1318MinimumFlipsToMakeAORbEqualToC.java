package pkg5leetcode.official75;

/*
 * Minimum Flips to Make a OR b Equal to c | LC 1318
 * APPROACH: Per bit compare a|b to c; count mismatches.
 * COMPLEXITY: Time O(1), Space O(1)
 */
public class official75_LC1318MinimumFlipsToMakeAORbEqualToC {
    static int minFlips(int a, int b, int c) {
        int flips = 0;
        for (int i = 0; i < 32; i++) {
            int bitC = (c >> i) & 1;
            int bitA = (a >> i) & 1;
            int bitB = (b >> i) & 1;
            if (bitC == 1) {
                if (bitA == 0 && bitB == 0) flips++;
            } else {
                if (bitA == 1 && bitB == 1) flips += 2;
                else if (bitA == 1 || bitB == 1) flips++;
            }
        }
        return flips;
    }

    public static void main(String[] args) {
        check(minFlips(2, 6, 5) == 3, "case1");
        check(minFlips(4, 2, 7) == 1, "case2");
        check(minFlips(1, 2, 3) == 0, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
