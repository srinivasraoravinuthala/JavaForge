package pkg5leetcode.blind75;

/*
 * Course Schedule | LC 207
 * APPROACH: Kahn topological sort detects cycle in directed graph.
 * COMPLEXITY: Time O(V+E), Space O(V+E)
 */
import java.util.*;

public class blind75_LC207CourseSchedule {
    static boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        int[] indeg = new int[numCourses];
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]);
            indeg[p[0]]++;
        }
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) if (indeg[i] == 0) q.add(i);
        int seen = 0;
        while (!q.isEmpty()) {
            int u = q.poll();
            seen++;
            for (int v : adj.get(u)) if (--indeg[v] == 0) q.add(v);
        }
        return seen == numCourses;
    }

    public static void main(String[] args) {
        check(canFinish(2, new int[][]{{1, 0}}), "case1");
        check(!canFinish(2, new int[][]{{1, 0}, {0, 1}}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
