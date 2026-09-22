package pkg5leetcode.blind75;

/*
 * Meeting Rooms II | LC 253
 * APPROACH: Min-heap of end times; reuse room if earliest ends before start.
 * COMPLEXITY: Time O(n log n), Space O(n)
 */
import java.util.*;

public class blind75_LC253MeetingRoomsII {
    static int minMeetingRooms(int[][] intervals) {
        if (intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(intervals[0][1]);
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= heap.peek()) heap.poll();
            heap.add(intervals[i][1]);
        }
        return heap.size();
    }

    public static void main(String[] args) {
        check(minMeetingRooms(new int[][]{{0, 30}, {5, 10}, {15, 20}}) == 2, "case1");
        check(minMeetingRooms(new int[][]{{7, 10}, {2, 4}}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
