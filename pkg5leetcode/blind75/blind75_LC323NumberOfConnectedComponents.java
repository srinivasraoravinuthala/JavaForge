package pkg5leetcode.blind75;

/*
 * Number of Connected Components in an Undirected Graph | LC 323
 * APPROACH: Union-Find merges edges; count distinct roots.
 * COMPLEXITY: Time O(n alpha(n)), Space O(n)
 */
public class blind75_LC323NumberOfConnectedComponents {
    static int countComponents(int n, int[][] edges) {
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        for (int[] e : edges) {
            int a = find(parent, e[0]), b = find(parent, e[1]);
            if (a != b) parent[a] = b;
        }
        int comps = 0;
        for (int i = 0; i < n; i++) if (find(parent, i) == i) comps++;
        return comps;
    }

    static int find(int[] p, int x) {
        while (p[x] != x) { p[x] = p[p[x]]; x = p[x]; }
        return x;
    }

    public static void main(String[] args) {
        check(countComponents(5, new int[][]{{0, 1}, {1, 2}, {3, 4}}) == 2, "case1");
        check(countComponents(5, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 4}}) == 1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
