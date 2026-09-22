package pkg5leetcode.blind75;

/*
 * Insert Interval | LC 57
 * APPROACH: Three phases: before, merge overlap, after new interval.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class blind75_LC57InsertInterval {
    static int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0, n = intervals.length;
        while (i < n && intervals[i][1] < newInterval[0]) res.add(intervals[i++]);
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        res.add(newInterval);
        while (i < n) res.add(intervals[i++]);
        return res.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] r = insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5});
        check(r.length == 2 && r[0][0] == 1 && r[0][1] == 5, "case1");
        int[][] r2 = insert(new int[][]{{1, 2}, {3, 5}, {6, 7}, {8, 10}, {12, 16}}, new int[]{4, 8});
        check(r2.length == 3 && r2[1][0] == 3 && r2[1][1] == 10, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
