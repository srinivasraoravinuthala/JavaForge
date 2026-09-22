package pkg5leetcode;

/*
 * LeetCode 56: Merge Intervals  (Medium)
 * --------------------------------------
 * Merge all overlapping intervals.
 *
 * APPROACH: sort by start; merge when current start <= last merged end.
 * COMPLEXITY: Time O(n log n) (sort), Space O(n) for output.
 */
import java.util.*;

public class leetcode8MergeIntervals {

    static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] cur : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < cur[0]) {
                merged.add(cur);                                   // no overlap
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], cur[1]);
            }
        }
        return merged.toArray(new int[0][]);
    }

    public static void main(String[] args) {
        int[][] r1 = merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        check(Arrays.deepEquals(r1, new int[][]{{1, 6}, {8, 10}, {15, 18}}), "overlap");
        int[][] r2 = merge(new int[][]{{1, 4}, {4, 5}});
        check(Arrays.deepEquals(r2, new int[][]{{1, 5}}), "touching");
        System.out.println("leetcode8MergeIntervals: all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
