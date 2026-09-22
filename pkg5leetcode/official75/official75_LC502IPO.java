package pkg5leetcode.official75;

/*
 * IPO | LC 502
 * APPROACH: Sort by capital; max-heap profits unlockable.
 * COMPLEXITY: Time O(n log n), Space O(n)
 */
import java.util.*;

public class official75_LC502IPO {
    static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) { projects[i][0] = capital[i]; projects[i][1] = profits[i]; }
        Arrays.sort(projects, (a, b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int i = 0;
        while (k-- > 0) {
            while (i < n && projects[i][0] <= w) pq.offer(projects[i++][1]);
            if (pq.isEmpty()) break;
            w += pq.poll();
        }
        return w;
    }

    public static void main(String[] args) {
        check(findMaximizedCapital(2, 0, new int[]{1,2,3}, new int[]{0,1,1}) == 4, "case1");
        check(findMaximizedCapital(3, 0, new int[]{1,2,3}, new int[]{0,1,2}) == 6, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
