package pkg3datastructures;

/*
 * datastructures11GraphImpl.java
 * --------------
 * A graph stored as an adjacency list, with BFS and DFS traversals.
 * Supports directed or undirected edges.
 *
 * COMPLEXITY: BFS/DFS O(V + E). Adjacency list space O(V + E).
 * WHEN TO USE: networks, maps, dependencies, social graphs.
 */
import java.util.*;

public class datastructures11GraphImpl {

    private final Map<Integer, List<Integer>> adj = new HashMap<>();
    private final boolean directed;

    datastructures11GraphImpl(boolean directed) { this.directed = directed; }

    void addEdge(int u, int v) {
        adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        adj.computeIfAbsent(v, k -> new ArrayList<>());      // ensure v exists
        if (!directed) adj.get(v).add(u);
    }

    List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        q.offer(start); seen.add(start);
        while (!q.isEmpty()) {
            int node = q.poll();
            order.add(node);
            for (int nb : adj.getOrDefault(node, List.of())) {
                if (seen.add(nb)) q.offer(nb);
            }
        }
        return order;
    }

    List<Integer> dfs(int start) {
        List<Integer> order = new ArrayList<>();
        dfs(start, new HashSet<>(), order);
        return order;
    }
    private void dfs(int node, Set<Integer> seen, List<Integer> order) {
        if (!seen.add(node)) return;
        order.add(node);
        for (int nb : adj.getOrDefault(node, List.of())) dfs(nb, seen, order);
    }

    public static void main(String[] args) {
        datastructures11GraphImpl g = new datastructures11GraphImpl(false);   // undirected
        g.addEdge(1, 2); g.addEdge(1, 3);
        g.addEdge(2, 4); g.addEdge(3, 4);
        g.addEdge(4, 5);

        System.out.println("BFS from 1: " + g.bfs(1));
        System.out.println("DFS from 1: " + g.dfs(1));
    }
}
