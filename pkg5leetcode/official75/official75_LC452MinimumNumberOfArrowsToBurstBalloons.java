package pkg5leetcode.official75;

/*
 * Minimum Number of Arrows to Burst Balloons | LC 452
 * APPROACH: Greedy sort by end; shoot when start > last end.
 * COMPLEXITY: Time O(n log n), Space O(1)
 */
import java.util.*;

public class official75_LC452MinimumNumberOfArrowsToBurstBalloons {
    static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        int arrows = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++)
            if (points[i][0] > end) { arrows++; end = points[i][1]; }
        return arrows;
    }

    public static void main(String[] args) {
        check(findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}}) == 2, "case1");
        check(findMinArrowShots(new int[][]{{1,2},{3,4},{5,6},{7,8}}) == 4, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
