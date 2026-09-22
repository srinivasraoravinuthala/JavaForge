package pkg3datastructures;

/*
 * datastructures9AVLTree.java
 * ------------
 * A self-balancing BST. After each insert it rebalances using rotations so the
 * height stays O(log n), guaranteeing O(log n) operations even in the worst case.
 *
 * BALANCE FACTOR = height(left) - height(right), kept in {-1, 0, 1}.
 * ROTATIONS: LL (right rotate), RR (left rotate), LR, RL.
 */
import java.util.*;

public class datastructures9AVLTree {

    static class Node {
        int val, height = 1; Node left, right;
        Node(int val) { this.val = val; }
    }

    private Node root;

    private int h(Node n) { return n == null ? 0 : n.height; }
    private int balance(Node n) { return n == null ? 0 : h(n.left) - h(n.right); }
    private void update(Node n) { n.height = 1 + Math.max(h(n.left), h(n.right)); }

    private Node rotateRight(Node y) {
        Node x = y.left, t = x.right;
        x.right = y; y.left = t;
        update(y); update(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right, t = y.left;
        y.left = x; x.right = t;
        update(x); update(y);
        return y;
    }

    void insert(int v) { root = insert(root, v); }
    private Node insert(Node n, int v) {
        if (n == null) return new Node(v);
        if (v < n.val) n.left = insert(n.left, v);
        else if (v > n.val) n.right = insert(n.right, v);
        else return n;

        update(n);
        int bf = balance(n);
        // Four imbalance cases
        if (bf > 1 && v < n.left.val) return rotateRight(n);                 // LL
        if (bf < -1 && v > n.right.val) return rotateLeft(n);                // RR
        if (bf > 1 && v > n.left.val) { n.left = rotateLeft(n.left); return rotateRight(n); }   // LR
        if (bf < -1 && v < n.right.val) { n.right = rotateRight(n.right); return rotateLeft(n); } // RL
        return n;
    }

    void inorder(Node n, List<Integer> out) { if (n == null) return; inorder(n.left, out); out.add(n.val); inorder(n.right, out); }

    public static void main(String[] args) {
        datastructures9AVLTree avl = new datastructures9AVLTree();
        // Inserting sorted values would skew a normal BST; AVL stays balanced.
        for (int i = 1; i <= 7; i++) avl.insert(i);

        List<Integer> in = new ArrayList<>();
        avl.inorder(avl.root, in);
        System.out.println("in-order: " + in);
        System.out.println("root value: " + avl.root.val + " (balanced, not 1)");
        System.out.println("tree height: " + avl.root.height + " (log2(7)~3, vs 7 if skewed)");
    }
}
