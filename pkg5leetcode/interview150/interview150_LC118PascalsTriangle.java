package pkg5leetcode.interview150;

/*
 * Pascal's Triangle | LC 118
 * APPROACH: Each row built from previous row sums of adjacent pairs.
 * COMPLEXITY: Time O(n^2), Space O(n^2)
 */
import java.util.*;

public class interview150_LC118PascalsTriangle {
    static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) row.add(1);
                else row.add(res.get(i - 1).get(j - 1) + res.get(i - 1).get(j));
            }
            res.add(row);
        }
        return res;
    }

    public static void main(String[] args) {
        List<List<Integer>> g = generate(3);
        check(g.size() == 3 && g.get(2).equals(Arrays.asList(1, 2, 1)), "case1");
        check(generate(1).get(0).equals(Arrays.asList(1)), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
