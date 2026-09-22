package pkg5leetcode.official75;

/*
 * Evaluate Division | LC 399
 * APPROACH: Build weighted graph; DFS find path product.
 * COMPLEXITY: Time O(q*(V+E)), Space O(V+E)
 */
import java.util.*;

public class official75_LC399EvaluateDivision {
    static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            double v = values[i];
            graph.computeIfAbsent(a, k -> new HashMap<>()).put(b, v);
            graph.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / v);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String s = queries.get(i).get(0), t = queries.get(i).get(1);
            if (!graph.containsKey(s) || !graph.containsKey(t)) res[i] = -1.0;
            else if (s.equals(t)) res[i] = 1.0;
            else {
                Set<String> seen = new HashSet<>();
                res[i] = dfs(s, t, graph, seen);
            }
        }
        return res;
    }

    static double dfs(String cur, String target, Map<String, Map<String, Double>> graph, Set<String> seen) {
        seen.add(cur);
        for (Map.Entry<String, Double> e : graph.get(cur).entrySet()) {
            if (seen.contains(e.getKey())) continue;
            if (e.getKey().equals(target)) return e.getValue();
            double sub = dfs(e.getKey(), target, graph, seen);
            if (sub >= 0) return e.getValue() * sub;
        }
        return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> eq = Arrays.asList(
                Arrays.asList("a","b"), Arrays.asList("b","c"));
        double[] vals = {2.0, 3.0};
        List<List<String>> q = Arrays.asList(
                Arrays.asList("a","c"), Arrays.asList("b","a"), Arrays.asList("a","e"));
        double[] r = calcEquation(eq, vals, q);
        check(Math.abs(r[0] - 6.0) < 1e-9 && Math.abs(r[1] - 0.5) < 1e-9 && r[2] == -1.0, "case1");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
