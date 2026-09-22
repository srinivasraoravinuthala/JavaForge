package pkg5leetcode.blind75;

/*
 * Graph Valid Tree | LC 261
 * APPROACH: n-1 edges and single connected component via Union-Find.
 * COMPLEXITY: Time O(n alpha(n)), Space O(n)
 */
public class blind75_LC261GraphValidTree {
    static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] e : edges) {
            int a = find(parent, e[0]), b = find(parent, e[1]);
            if (a == b) return false;
            parent[a] = b;
        }
        return true;
    }

    static int find(int[] p, int x) {
        while (p[x] != x) { p[x] = p[p[x]]; x = p[x]; }
        return x;
    }

    public static void main(String[] args) {
        check(validTree(5, new int[][]{{0, 1}, {0, 2}, {0, 3}, {1, 4}}), "case1");
        check(!validTree(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {1, 3}, {1, 4}}), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
