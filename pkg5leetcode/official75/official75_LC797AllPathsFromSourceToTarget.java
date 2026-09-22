package pkg5leetcode.official75;

/*
 * All Paths From Source to Target | LC 797
 * APPROACH: Backtracking DFS build path to target.
 * COMPLEXITY: Time O(2^n), Space O(n)
 */
import java.util.*;

public class official75_LC797AllPathsFromSourceToTarget {
    static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(0, graph, path, res);
        return res;
    }

    static void dfs(int node, int[][] graph, List<Integer> path, List<List<Integer>> res) {
        if (node == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int nxt : graph[node]) {
            path.add(nxt);
            dfs(nxt, graph, path, res);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> r = allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}});
        check(r.size() == 2, "case1");
        check(allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}}).size() == 2, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
