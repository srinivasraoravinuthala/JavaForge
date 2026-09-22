package pkg5leetcode.official75;

/*
 * Number of Recent Calls | LC 933
 * APPROACH: Queue evict calls older than t-3000.
 * COMPLEXITY: Time O(1) amortized, Space O(n)
 */
import java.util.*;

public class official75_LC933NumberOfRecentCalls {
    static class RecentCounter {
        Deque<Integer> q = new ArrayDeque<>();

        int ping(int t) {
            q.addLast(t);
            while (q.peekFirst() < t - 3000) q.pollFirst();
            return q.size();
        }
    }

    public static void main(String[] args) {
        RecentCounter rc = new RecentCounter();
        check(rc.ping(1) == 1, "case1");
        check(rc.ping(100) == 2, "case2");
        check(rc.ping(3001) == 3, "case3");
        check(rc.ping(3002) == 3, "case4");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
