package pkg3datastructures;

/*
 * datastructures12UnionFind.java  (Disjoint Set Union, DSU)
 * -----------------------------------------
 * Tracks a partition of elements into disjoint sets with near-constant-time
 * union and find, using PATH COMPRESSION + UNION BY RANK.
 *
 * COMPLEXITY: ~O(alpha(n)) per op (inverse Ackermann, effectively constant).
 * WHEN TO USE: connectivity, Kruskal's MST, cycle detection, grouping.
 */
public class datastructures12UnionFind {

    private final int[] parent, rank;
    private int components;

    datastructures12UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        components = n;
        for (int i = 0; i < n; i++) parent[i] = i;   // each element is its own set
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);  // path compression
        return parent[x];
    }

    boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;                        // already connected
        if (rank[ra] < rank[rb]) { int t = ra; ra = rb; rb = t; }
        parent[rb] = ra;                                   // attach smaller under larger
        if (rank[ra] == rank[rb]) rank[ra]++;
        components--;
        return true;
    }

    boolean connected(int a, int b) { return find(a) == find(b); }
    int components() { return components; }

    public static void main(String[] args) {
        datastructures12UnionFind uf = new datastructures12UnionFind(6);   // elements 0..5
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);
        System.out.println("connected(0,2): " + uf.connected(0, 2));   // true
        System.out.println("connected(0,3): " + uf.connected(0, 3));   // false
        System.out.println("components: " + uf.components());          // {0,1,2} {3,4} {5} -> 3
        uf.union(2, 4);
        System.out.println("after union(2,4) connected(0,3): " + uf.connected(0, 3));
        System.out.println("components: " + uf.components());
    }
}
