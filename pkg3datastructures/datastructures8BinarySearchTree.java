package pkg3datastructures;

/*
 * datastructures8BinarySearchTree.java
 * ---------------------
 * A BST: left < node < right. Supports insert, search, delete, and in-order
 * traversal (which yields sorted output).
 *
 * COMPLEXITY: O(h) per op where h is height; O(log n) if balanced, O(n) if skewed.
 * WHEN TO USE: ordered data with fast search/insert/delete; range queries.
 */
import java.util.*;

public class datastructures8BinarySearchTree {

    static class Node {
        int val; Node left, right;
        Node(int val) { this.val = val; }
    }

    private Node root;

    void insert(int v) { root = insert(root, v); }
    private Node insert(Node n, int v) {
        if (n == null) return new Node(v);
        if (v < n.val) n.left = insert(n.left, v);
        else if (v > n.val) n.right = insert(n.right, v);   // ignore duplicates
        return n;
    }

    boolean contains(int v) {
        Node n = root;
        while (n != null) {
            if (v == n.val) return true;
            n = v < n.val ? n.left : n.right;
        }
        return false;
    }

    void delete(int v) { root = delete(root, v); }
    private Node delete(Node n, int v) {
        if (n == null) return null;
        if (v < n.val) n.left = delete(n.left, v);
        else if (v > n.val) n.right = delete(n.right, v);
        else {
            // Found: handle 0, 1, or 2 children
            if (n.left == null) return n.right;
            if (n.right == null) return n.left;
            Node successor = min(n.right);     // in-order successor
            n.val = successor.val;
            n.right = delete(n.right, successor.val);
        }
        return n;
    }

    private Node min(Node n) { while (n.left != null) n = n.left; return n; }

    void inorder(Node n, List<Integer> out) { if (n == null) return; inorder(n.left, out); out.add(n.val); inorder(n.right, out); }
    List<Integer> sorted() { List<Integer> out = new ArrayList<>(); inorder(root, out); return out; }

    public static void main(String[] args) {
        datastructures8BinarySearchTree bst = new datastructures8BinarySearchTree();
        for (int x : new int[]{50, 30, 70, 20, 40, 60, 80}) bst.insert(x);
        System.out.println("in-order (sorted): " + bst.sorted());
        System.out.println("contains 60: " + bst.contains(60) + " | contains 99: " + bst.contains(99));

        bst.delete(20);                 // leaf
        bst.delete(30);                 // one child
        bst.delete(50);                 // two children (root)
        System.out.println("after deletes: " + bst.sorted());
    }
}
