package pkg3datastructures;

/*
 * datastructures7BinaryTree.java
 * ---------------
 * A binary tree with the four traversals (pre/in/post-order DFS and
 * level-order BFS), height, and node count.
 *
 * COMPLEXITY: each traversal O(n); height O(n).
 * NOTE: a plain binary tree has no ordering rule (unlike a BST).
 */
import java.util.*;

public class datastructures7BinaryTree {

    static class Node {
        int val; Node left, right;
        Node(int val) { this.val = val; }
    }

    static void preorder(Node n, List<Integer> out)  { if (n == null) return; out.add(n.val); preorder(n.left, out); preorder(n.right, out); }
    static void inorder(Node n, List<Integer> out)   { if (n == null) return; inorder(n.left, out); out.add(n.val); inorder(n.right, out); }
    static void postorder(Node n, List<Integer> out) { if (n == null) return; postorder(n.left, out); postorder(n.right, out); out.add(n.val); }

    static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) return levels;
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int n = q.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                Node cur = q.poll();
                level.add(cur.val);
                if (cur.left != null) q.offer(cur.left);
                if (cur.right != null) q.offer(cur.right);
            }
            levels.add(level);
        }
        return levels;
    }

    static int height(Node n) { return n == null ? 0 : 1 + Math.max(height(n.left), height(n.right)); }
    static int count(Node n)  { return n == null ? 0 : 1 + count(n.left) + count(n.right); }

    public static void main(String[] args) {
        //        1
        //       / \
        //      2   3
        //     / \   \
        //    4   5   6
        Node root = new Node(1);
        root.left = new Node(2); root.right = new Node(3);
        root.left.left = new Node(4); root.left.right = new Node(5);
        root.right.right = new Node(6);

        List<Integer> pre = new ArrayList<>(), in = new ArrayList<>(), post = new ArrayList<>();
        preorder(root, pre); inorder(root, in); postorder(root, post);
        System.out.println("preorder:  " + pre);
        System.out.println("inorder:   " + in);
        System.out.println("postorder: " + post);
        System.out.println("levelOrder: " + levelOrder(root));
        System.out.println("height=" + height(root) + " count=" + count(root));
    }
}
