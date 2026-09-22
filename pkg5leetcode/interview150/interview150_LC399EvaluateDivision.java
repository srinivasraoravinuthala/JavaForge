package pkg5leetcode.interview150;

/*
 * Evaluate Division | LC 399
 * APPROACH: Build weighted graph; DFS/BFS to find quotient path.
 * COMPLEXITY: Time O(q*(V+E)), Space O(V+E)
 */
import java.util.*;

public class interview150_LC399EvaluateDivision {
    static double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> g = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            g.computeIfAbsent(a, k -> new HashMap<>()).put(b, values[i]);
            g.computeIfAbsent(b, k -> new HashMap<>()).put(a, 1.0 / values[i]);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String x = queries.get(i).get(0), y = queries.get(i).get(1);
            if (!g.containsKey(x) || !g.containsKey(y)) res[i] = -1.0;
            else res[i] = dfs(x, y, g, new HashSet<>());
        }
        return res;
    }

    static double dfs(String cur, String target, Map<String, Map<String, Double>> g, Set<String> seen) {
        if (cur.equals(target)) return 1.0;
        seen.add(cur);
        for (Map.Entry<String, Double> e : g.get(cur).entrySet()) {
            if (!seen.contains(e.getKey())) {
                double d = dfs(e.getKey(), target, g, seen);
                if (d > 0) return e.getValue() * d;
            }
        }
        return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> eq = Arrays.asList(
            Arrays.asList("a", "b"), Arrays.asList("b", "c"));
        double[] val = {2.0, 3.0};
        List<List<String>> q = Arrays.asList(
            Arrays.asList("a", "c"), Arrays.asList("b", "a"), Arrays.asList("a", "e"));
        double[] r = calcEquation(eq, val, q);
        check(r[0] == 6.0, "case1");
        check(r[1] == 0.5, "case2");
        check(r[2] == -1.0, "case3");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
