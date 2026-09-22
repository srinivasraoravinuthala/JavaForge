package pkg5leetcode.blind75;

/*
 * Alien Dictionary | LC 269
 * APPROACH: Build char graph from sorted words; topological sort or cycle check.
 * COMPLEXITY: Time O(C) total chars, Space O(1) alphabet
 */
import java.util.*;

public class blind75_LC269AlienDictionary {
    static String alienOrder(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indeg = new HashMap<>();
        for (String w : words)
            for (char c : w.toCharArray()) {
                adj.putIfAbsent(c, new HashSet<>());
                indeg.putIfAbsent(c, 0);
            }
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1];
            if (w1.length() > w2.length() && w1.startsWith(w2)) return "";
            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char c1 = w1.charAt(j), c2 = w2.charAt(j);
                if (c1 != c2) {
                    if (!adj.get(c1).contains(c2)) {
                        adj.get(c1).add(c2);
                        indeg.put(c2, indeg.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        Deque<Character> q = new ArrayDeque<>();
        for (char c : indeg.keySet()) if (indeg.get(c) == 0) q.add(c);
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char c = q.poll();
            sb.append(c);
            for (char nb : adj.get(c)) {
                int d = indeg.get(nb) - 1;
                indeg.put(nb, d);
                if (d == 0) q.add(nb);
            }
        }
        return sb.length() == indeg.size() ? sb.toString() : "";
    }

    public static void main(String[] args) {
        check(alienOrder(new String[]{"wrt", "wrf", "er", "ett", "rftt"}).equals("wertf"), "case1");
        check(alienOrder(new String[]{"z", "x"}).equals("zx"), "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
