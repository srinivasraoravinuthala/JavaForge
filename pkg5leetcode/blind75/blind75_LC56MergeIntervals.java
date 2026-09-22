package pkg5leetcode.blind75;

/*
 * Merge Intervals | LC 56
 * APPROACH: Sort by start; merge if overlap else push.
 * COMPLEXITY: Time O(n log n), Space O(n)
 */
import java.util.*;

public class blind75_LC56MergeIntervals {
    static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> res = new ArrayList<>();
        for (int[] iv : intervals) {
            if (res.isEmpty() || res.get(res.size() - 1)[1] < iv[0]) res.add(iv);
            else res.get(res.size() - 1)[1] = Math.max(res.get(res.size() - 1)[1], iv[1]);
        }
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] r = merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        check(r.length == 3 && r[0][1] == 6, "case1");
        int[][] r2 = merge(new int[][]{{1, 4}, {4, 5}});
        check(r2.length == 1 && r2[0][1] == 5, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
