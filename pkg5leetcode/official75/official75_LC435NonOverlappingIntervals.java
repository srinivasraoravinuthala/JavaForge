package pkg5leetcode.official75;

/*
 * Non-overlapping Intervals | LC 435
 * APPROACH: Greedy by earliest end time (activity selection).
 * COMPLEXITY: Time O(n log n), Space O(1)
 */
import java.util.*;

public class official75_LC435NonOverlappingIntervals {
    static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int end = intervals[0][1], kept = 1;
        for (int i = 1; i < intervals.length; i++)
            if (intervals[i][0] >= end) { kept++; end = intervals[i][1]; }
        return intervals.length - kept;
    }

    public static void main(String[] args) {
        check(eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}) == 1, "case1");
        check(eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}) == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
