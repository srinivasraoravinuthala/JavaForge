package pkg4algorithms;

/*
 * algorithms6GraphAlgorithms.java
 * --------------------
 * Dijkstra (shortest path), topological sort (Kahn's), and cycle detection
 * in a directed graph.
 *
 * COMPLEXITY: Dijkstra O((V+E) log V) with a heap; topo sort & cycle O(V+E).
 */
import java.util.*;

public class algorithms6GraphAlgorithms {

    // Dijkstra: shortest distances from src in a weighted graph with non-negative edges.
    static int[] dijkstra(int n, int[][] edges, int src) {
        List<int[]>[] adj = buildWeighted(n, edges);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[0], d = cur[1];
            if (d > dist[u]) continue;                 // stale entry
            for (int[] e : adj[u]) {
                int v = e[0], w = e[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    // Topological sort via Kahn's algorithm (BFS on in-degrees). Returns empty if cyclic.
    static List<Integer> topoSort(int n, int[][] edges) {
        List<Integer>[] adj = buildDirected(n, edges);
        int[] indeg = new int[n];
        for (int[] e : edges) indeg[e[1]]++;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) if (indeg[i] == 0) q.offer(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : adj[u]) if (--indeg[v] == 0) q.offer(v);
        }
        return order.size() == n ? order : List.of();   // cycle if not all included
    }

    // Detect a cycle in a directed graph using DFS colors.
    static boolean hasCycle(int n, int[][] edges) {
        List<Integer>[] adj = buildDirected(n, edges);
        int[] color = new int[n];   // 0=white,1=gray(in stack),2=black(done)
        for (int i = 0; i < n; i++) if (color[i] == 0 && dfsCycle(i, adj, color)) return true;
        return false;
    }
    static boolean dfsCycle(int u, List<Integer>[] adj, int[] color) {
        color[u] = 1;
        for (int v : adj[u]) {
            if (color[v] == 1) return true;            // back edge -> cycle
            if (color[v] == 0 && dfsCycle(v, adj, color)) return true;
        }
        color[u] = 2;
        return false;
    }

    @SuppressWarnings("unchecked")
    static List<int[]>[] buildWeighted(int n, int[][] edges) {
        List<int[]>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) adj[e[0]].add(new int[]{e[1], e[2]});
        return adj;
    }
    @SuppressWarnings("unchecked")
    static List<Integer>[] buildDirected(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) adj[e[0]].add(e[1]);
        return adj;
    }

    public static void main(String[] args) {
        // Weighted directed graph: {from, to, weight}
        int[][] wedges = {{0, 1, 4}, {0, 2, 1}, {2, 1, 2}, {1, 3, 1}, {2, 3, 5}};
        System.out.println("Dijkstra from 0: " + Arrays.toString(dijkstra(4, wedges, 0)));

        // DAG for topo sort: {from, to}
        int[][] dag = {{5, 2}, {5, 0}, {4, 0}, {4, 1}, {2, 3}, {3, 1}};
        System.out.println("Topological order: " + topoSort(6, dag));

        int[][] acyclic = {{0, 1}, {1, 2}};
        int[][] cyclic = {{0, 1}, {1, 2}, {2, 0}};
        System.out.println("hasCycle(acyclic): " + hasCycle(3, acyclic));
        System.out.println("hasCycle(cyclic):  " + hasCycle(3, cyclic));
    }
}
