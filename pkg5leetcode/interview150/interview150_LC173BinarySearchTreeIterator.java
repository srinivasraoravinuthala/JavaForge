package pkg5leetcode.interview150;

/*
 * Binary Search Tree Iterator | LC 173
 * APPROACH: Stack pushes left spine; pop then push left of right child.
 * COMPLEXITY: Time O(1) amortized, Space O(h)
 */
import java.util.*;

public class interview150_LC173BinarySearchTreeIterator {
    /** Same shape as pkg5leetcode/common/TreeNode.java (nested for single-file runs). */

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class BSTIterator {
        Deque<TreeNode> st = new ArrayDeque<>();

        BSTIterator(TreeNode root) {
            pushLeft(root);
        }

        int next() {
            TreeNode n = st.pop();
            pushLeft(n.right);
            return n.val;
        }

        boolean hasNext() { return !st.isEmpty(); }

        void pushLeft(TreeNode node) {
            while (node != null) {
                st.push(node);
                node = node.left;
            }
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);
        BSTIterator it = new BSTIterator(root);
        check(it.next() == 3, "case1");
        check(it.next() == 7, "case2");
        check(it.hasNext(), "case3");
        check(it.next() == 9, "case4");
        System.out.println("all tests passed");
    }

    static void check(boolean cond, String name) {
        if (!cond) throw new AssertionError("FAILED: " + name);
        System.out.println("  PASS " + name);
    }
}
