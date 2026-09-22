package pkg5leetcode.interview150;

/*
 * Cheapest Flights Within K Stops | LC 787
 * APPROACH: Bellman-Ford style relax edges for k+1 iterations.
 * COMPLEXITY: Time O(k*E), Space O(n)
 */
public class interview150_LC787CheapestFlightsWithinKStops {
    static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        for (int i = 0; i < n; i++) dist[i] = Integer.MAX_VALUE / 2;
        dist[src] = 0;
        for (int i = 0; i <= k; i++) {
            int[] next = dist.clone();
            for (int[] f : flights) {
                if (dist[f[0]] + f[2] < next[f[1]])
                    next[f[1]] = dist[f[0]] + f[2];
            }
            dist = next;
        }
        return dist[dst] >= Integer.MAX_VALUE / 2 ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        check(findCheapestPrice(4, flights, 0, 3, 1) == 700, "case1");
        check(findCheapestPrice(3, new int[][]{{0,1,100},{1,2,100},{0,2,500}}, 0, 2, 1) == 200, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
