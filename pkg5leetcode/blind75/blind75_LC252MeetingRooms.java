package pkg5leetcode.blind75;

/*
 * Meeting Rooms | LC 252
 * APPROACH: Sort by start; check no overlap between consecutive.
 * COMPLEXITY: Time O(n log n), Space O(1)
 */
import java.util.*;

public class blind75_LC252MeetingRooms {
    static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i < intervals.length; i++)
            if (intervals[i][0] < intervals[i - 1][1]) return false;
        return true;
    }

    public static void main(String[] args) {
        check(!canAttendMeetings(new int[][]{{0, 30}, {5, 10}, {15, 20}}), "case1");
        check(canAttendMeetings(new int[][]{{7, 10}, {2, 4}}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
