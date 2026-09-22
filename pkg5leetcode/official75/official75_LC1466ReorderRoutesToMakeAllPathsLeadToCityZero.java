package pkg5leetcode.official75;

/*
 * Reorder Routes to Make All Paths Lead to City Zero | LC 1466
 * APPROACH: BFS/DFS tree from 0; count wrong-direction edges.
 * COMPLEXITY: Time O(n), Space O(n)
 */
import java.util.*;

public class official75_LC1466ReorderRoutesToMakeAllPathsLeadToCityZero {
    static int minReorder(int n, int[][] connections) {
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (int[] c : connections)
            adj.get(c[0]).add(new int[]{c[1], 1});
        for (int[] c : connections)
            adj.get(c[1]).add(new int[]{c[0], 0});
        boolean[] seen = new boolean[n];
        Deque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0});
        seen[0] = true;
        int flips = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] e : adj.get(cur[0])) {
                if (seen[e[0]]) continue;
                seen[e[0]] = true;
                flips += e[1];
                q.add(new int[]{e[0], 0});
            }
        }
        return flips;
    }

    public static void main(String[] args) {
        check(minReorder(6, new int[][]{{0,1},{1,3},{2,3},{4,0},{4,5}}) == 3, "case1");
        check(minReorder(5, new int[][]{{1,0},{1,2},{3,2},{3,4}}) == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
