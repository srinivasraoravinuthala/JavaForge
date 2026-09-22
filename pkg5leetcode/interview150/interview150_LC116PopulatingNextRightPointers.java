package pkg5leetcode.interview150;

/*
 * Populating Next Right Pointers in Each Node | LC 116
 * APPROACH: Level-order connect siblings using previously established links.
 * COMPLEXITY: Time O(n), Space O(1)
 */
public class interview150_LC116PopulatingNextRightPointers {
    static class Node {
        int val;
        Node left, right, next;
        Node(int val) { this.val = val; }
    }

    static Node connect(Node root) {
        if (root == null) return null;
        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) head.right.next = head.next.left;
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        connect(root);
        check(root.next == null && root.left.next == root.right, "case1");
        check(root.left.left.next == root.left.right, "case2");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
