package pkg5leetcode.blind75;

/*
 * Clone Graph | LC 133
 * APPROACH: BFS/DFS with HashMap old->clone node.
 * COMPLEXITY: Time O(V+E), Space O(V)
 */
import java.util.*;

public class blind75_LC133CloneGraph {
    static class Node {
        int val;
        List<Node> neighbors = new ArrayList<>();
        Node(int val) { this.val = val; }
    }

    static Node cloneGraph(Node node) {
        if (node == null) return null;
        Map<Node, Node> map = new HashMap<>();
        Deque<Node> q = new ArrayDeque<>();
        map.put(node, new Node(node.val));
        q.add(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (Node nb : cur.neighbors) {
                if (!map.containsKey(nb)) {
                    map.put(nb, new Node(nb.val));
                    q.add(nb);
                }
                map.get(cur).neighbors.add(map.get(nb));
            }
        }
        return map.get(node);
    }

    public static void main(String[] args) {
        Node n1 = new Node(1), n2 = new Node(2);
        n1.neighbors.add(n2); n2.neighbors.add(n1);
        Node c = cloneGraph(n1);
        check(c.val == 1 && c.neighbors.size() == 1, "case1");
        check(c.neighbors.get(0).val == 2 && c != n1, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
